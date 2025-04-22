import { MedicoService } from './../../../servicios/medico.service';
import { LoginService } from './../../../servicios/login.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { Medico } from '../../../modelos/medico.model';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-actualizar-datos',
  standalone: true,
  imports: [FormsModule, CommonModule, HttpClientModule, ReactiveFormsModule, RouterModule],
  templateUrl: './actualizar-datos.component.html',
  styleUrls: ['./actualizar-datos.component.css']
})
export class ActualizarDatosComponent implements OnInit {
  medicoForm!: FormGroup;
  idMedico!: number;
  loading = false;
  errorMsg = '';
  medico: Medico | null = null;

  constructor(
    private fb: FormBuilder,
    private LoginService: LoginService,
    private MedicoService: MedicoService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const usuario = this.LoginService.obtenerUsuario();
    this.idMedico = usuario.id;
    this.initForm();
    this.cargarDatosMedico();
  }

  private initForm(): void {
    this.medicoForm = this.fb.group({
      telefonoMedico: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
      direccionMedico: ['', Validators.required],
      correoMedico: ['', [Validators.required, Validators.email]],
      residenciaMedico: ['', Validators.required]
    });
  }

  private cargarDatosMedico(): void {
    this.loading = true;

    if (!this.idMedico) {
      this.errorMsg = 'No se pudo cargar los datos del médico. El ID es inválido.';
      this.loading = false;
      return;
    }

    this.MedicoService.obtenerDatosMedico(this.idMedico).subscribe({
      next: (medico: Medico) => {
        this.medico = medico;
        this.medicoForm.patchValue({
          telefonoMedico: medico.telefonoMedico,
          direccionMedico: medico.direccionMedico,
          correoMedico: medico.correoMedico,
          residenciaMedico: medico.residenciaMedico
        });
        this.loading = false;
      },
      error: (err) => {
        this.errorMsg = 'No se pudieron cargar los datos. Intenta más tarde.';
        console.error('Error al obtener los datos del médico', err);
        this.loading = false;
      }
    });
  }

  onSubmit(): void {
    if (this.medicoForm.invalid) return;

    if (typeof this.idMedico !== 'number' || isNaN(this.idMedico)) {
      this.errorMsg = 'ID del médico no válido';
      return;
    }

    const datosActualizados: Medico = {
      ...this.medico!,
      ...this.medicoForm.value,
      idMedico: this.idMedico
    };

    this.MedicoService.actualizarDatosMedico(this.idMedico, datosActualizados).subscribe({
      next: () => {
        // Mostrar la alerta de éxito
        window.alert('Datos actualizados con éxito');
        
        // Redirigir después de mostrar la alerta
        setTimeout(() => {
          this.router.navigate(['/menu-medico']);
        }, 1000);  // Espera 1 segundo para mostrar la alerta
      },
      error: (err) => {
        this.errorMsg = 'Error al actualizar los datos. Intenta nuevamente.';
        console.error('Error al actualizar los datos del médico', err);
      }
    });
  }
}
