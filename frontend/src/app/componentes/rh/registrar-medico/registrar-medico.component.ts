import { Component } from '@angular/core';
import { MedicoService } from '../../../servicios/medico.service';
import { FormBuilder, FormGroup, Validators, AbstractControl, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

@Component({
  selector: 'app-registro-medico',
  standalone: true,
  imports: [CommonModule, HttpClientModule, RouterModule, ReactiveFormsModule],
  templateUrl: './registrar-medico.component.html',
  styleUrls: ['./registrar-medico.component.css']
})
export class RegistrarMedicoComponent {
  medicoForm: FormGroup;

  constructor(private medicoService: MedicoService, private fb: FormBuilder) {
    this.medicoForm = this.fb.group({
      idMedico: this.fb.control(
        '', 
        {
          validators: [Validators.required, Validators.minLength(5)],
          asyncValidators: [this.cedulaUnicaValidator()],
          updateOn: 'blur' // 👈 importante: ejecuta validador asíncrono al salir del campo
        }
      ),
      nombreMedico: ['', [Validators.required, Validators.pattern(/^[a-zA-Z ]+$/)]],
      apellidoMedico: ['', [Validators.required, Validators.pattern(/^[a-zA-Z ]+$/)]],
      telefonoMedico: ['', [Validators.required, Validators.pattern(/^[0-9]{10}$/)]],
      direccionMedico: ['', [Validators.required]],
      fechaIngreso: ['', [Validators.required]],
      edadMedico: [null, [Validators.required, Validators.min(18), Validators.max(65)]],
      rolMedico: ['', [Validators.required]],
      turnoMedico: ['', [Validators.required]],
      correoMedico: ['', [Validators.required, Validators.email]],
      salarioMedico: [null, [Validators.required, Validators.min(0)]],
      residenciaMedico: ['', [Validators.required]],
      cargoMedico: ['', [Validators.required]]
    });
  }

  cedulaUnicaValidator() {
    return (control: AbstractControl): Observable<any> => {
      return this.medicoService.validarCedula(control.value).pipe(
        map(existe => {
          console.log('Resultado validación cédula:', existe);
          return existe ? null : { cedulaExistente: true }; // Si NO existe, muestra el error
        }),
        catchError(() => of(null)) // Si hay un error, no se marca como error
      );
    };
  }
  
  

  onSubmit(): void {
    // Detener si aún está pendiente la validación
    if (this.medicoForm.pending) {
      console.log('Esperando validación asíncrona...');
      return;
    }

    if (this.medicoForm.invalid) {
      console.log('Formulario inválido:', this.medicoForm.errors);
      return;
    }

    const medico = this.medicoForm.value;
    this.medicoService.registrarMedico(medico).subscribe(
      response => {
        console.log('Médico registrado con éxito:', response);
        alert('Médico registrado con éxito');
      },
      error => {
        console.error('Error al registrar médico:', error);
        alert('Error al registrar médico');
      }
    );
  }
}
