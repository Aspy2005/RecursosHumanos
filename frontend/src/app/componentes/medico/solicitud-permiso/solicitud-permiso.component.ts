import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-solicitud-permiso',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule, RouterModule],
  templateUrl: './solicitud-permiso.component.html',
  styleUrls: ['./solicitud-permiso.component.css']
})
export class SolicitudPermisoComponent implements OnInit {
  permisoForm!: FormGroup;
  idMedico!: number;

  constructor(
    private fb: FormBuilder,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    const usuario = JSON.parse(localStorage.getItem('usuarioActual') || '{}');
    this.idMedico = usuario.id;

    this.permisoForm = this.fb.group({
      tipo: ['Vacaciones', Validators.required],
      fechaInicio: ['', Validators.required],
      fechaFin: ['', Validators.required],
      motivo: ['', Validators.required],
      observaciones: ['']
    }, { validators: this.validarFechas });
  }

  validarFechas(group: FormGroup) {
    const inicio = new Date(group.get('fechaInicio')?.value);
    const fin = new Date(group.get('fechaFin')?.value);

    if (inicio && fin && fin < inicio) {
      group.get('fechaFin')?.setErrors({ fechaInvalida: true });
    } else {
      const errores = group.get('fechaFin')?.errors;
      if (errores) {
        delete errores['fechaInvalida'];
        if (Object.keys(errores).length === 0) {
          group.get('fechaFin')?.setErrors(null);
        }
      }
    }

    return null;
  }

  enviarSolicitud() {
    if (this.permisoForm.invalid) return;

    const inicio = new Date(this.permisoForm.value.fechaInicio);
    const fin = new Date(this.permisoForm.value.fechaFin);
    const diasSolicitados = Math.floor((fin.getTime() - inicio.getTime()) / (1000 * 60 * 60 * 24)) + 1;

    const solicitud = {
      idMedico: this.idMedico,
      tipo: this.permisoForm.value.tipo,
      fechaInicio: inicio,
      fechaFin: fin,
      motivo: this.permisoForm.value.motivo,
      observaciones: this.permisoForm.value.observaciones,
      diasSolicitados: diasSolicitados
    };

    this.http.post<any>('http://localhost:8080/api/solicitudes/solicitar', solicitud)
      .subscribe({
        next: () => {
          window.alert('Solicitud enviada con éxito, pendiente de aprobación');
          this.permisoForm.reset();
        },
        error: (err) => {
          console.error(err);
          window.alert('Hubo un error al enviar la solicitud');
        }
      });
  }
}
