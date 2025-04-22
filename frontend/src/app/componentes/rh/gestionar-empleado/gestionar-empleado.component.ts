import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MedicoService } from '../../../servicios/medico.service';
import { Medico } from '../../../modelos/medico.model';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-gestionar-empleado',
  standalone: true,
  imports: [FormsModule, CommonModule, HttpClientModule, ReactiveFormsModule, RouterModule],
  templateUrl: './gestionar-empleado.component.html',
  styleUrls: ['./gestionar-empleado.component.css']
})
export class GestionarEmpleadoComponent implements OnInit {
  medicoForm: FormGroup;
  mensaje: string = '';
  listaMedicos: Medico[] = [];

  constructor(
    private fb: FormBuilder,
    private medicoService: MedicoService
  ) {
    this.medicoForm = this.fb.group({
      idMedico: ['', Validators.required],
      nombreMedico: ['', Validators.required],
      apellidoMedico: ['', Validators.required],
      telefonoMedico: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
      edadMedico: ['', [Validators.required, Validators.min(18), Validators.max(65)]],
      correoMedico: ['', [Validators.required, Validators.email]],
      direccionMedico: ['', Validators.required],
      fechaIngreso: ['', Validators.required],
      rolMedico: ['', Validators.required],
      turnoMedico: ['', Validators.required],
      salarioMedico: ['', [Validators.required, Validators.min(1)]],
      residenciaMedico: ['', Validators.required],
      cargoMedico: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.cargarMedicosActivos();
  }

  cargarMedicosActivos() {
    this.medicoService.obtenerMedicosActivos().subscribe({
      next: (data) => this.listaMedicos = data,
      error: () => this.mensaje = 'Error al cargar los médicos.'
    });
  }

  seleccionarMedico(medico: Medico) {
    this.medicoForm.patchValue(medico);
    this.mensaje = '';
  }

  buscarMedico() {
    const cedula = this.medicoForm.get('idMedico')?.value;
    this.medicoService.obtenerDatosMedico(cedula).subscribe({
      next: (medico: Medico) => {
        if (medico) {
          if (medico.estado === 'INACTIVO') {
            this.mensaje = 'Este médico está INACTIVO y no se puede editar, debe reactivarlo para hacer eso.';
            this.medicoForm.reset();
            return;
          }
  
          // 🔥 Asegúrate que la fecha esté en formato YYYY-MM-DD
          if (medico.fechaIngreso) {
            medico.fechaIngreso = medico.fechaIngreso.split('T')[0];
          }
  
          this.medicoForm.patchValue(medico);
          this.mensaje = '';
        } else {
          this.mensaje = 'No se encontró al médico con esa cédula.';
        }
      },
      error: () => this.mensaje = 'Error al buscar el médico.'
    });
  }
  
  

  actualizarMedico() {
    const medico = this.medicoForm.value;
    this.medicoService.actualizarDatosMedico(medico.idMedico, medico).subscribe({
      next: () => this.mensaje = 'Datos actualizados correctamente ',
      error: () => this.mensaje = 'Error al actualizar los datos.'
    });
  }

  inactivarMedico() {
    const cedula = this.medicoForm.get('idMedico')?.value;
    if (confirm('¿Deseas marcar como INACTIVO a este médico?')) {
      this.medicoService.inactivarMedico(cedula).subscribe({
        next: () => {
          this.mensaje = 'Médico marcado como INACTIVO ';
          this.medicoForm.reset();
        },
        error: () => this.mensaje = 'Error al inactivar el médico.'
      });
    }
  }

  reactivarMedico() {
    const id = this.medicoForm.get('idMedico')?.value;
    if (!id) {
      this.mensaje = 'Debe buscar un médico primero.';
      return;
    }
  
    this.medicoService.reactivarMedico(id).subscribe({
      next: () => {
        this.mensaje = 'Médico reactivado correctamente.';
        this.buscarMedico(); // Para recargar datos actualizados
      },
      error: () => {
        this.mensaje = 'Error al reactivar el médico.';
      }
    });
  }
  
  
}
