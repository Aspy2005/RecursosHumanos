import { MedicoService } from '../../../servicios/medico.service';
import { Component } from '@angular/core';
import { Medico } from '../../../modelos/medico.model';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-registro-medico',
  standalone: true,
  imports: [FormsModule, CommonModule, ReactiveFormsModule, HttpClientModule, RouterModule],
  templateUrl: './registrar-medico.component.html',
  styleUrls: ['./registrar-medico.component.css']
})
export class RegistroMedicoComponent {

  // Inicializamos las propiedades numéricas como null, en lugar de 0.
  medico: Medico = {
    idMedico: null,
    nombreMedico: '',
    apellidoMedico: '',
    telefonoMedico: '',
    direccionMedico: '',
    fechaIngreso: '',
    edadMedico: null,
    rolMedico: '',
    turnoMedico: '',
    correoMedico: '',
    salarioMedico: null,
    residenciaMedico: '',
    cargoMedico: ''
  };

  constructor(private medicoService: MedicoService) { }

  // Método que se llama al enviar el formulario
  onSubmit(): void {
    this.medicoService.registrarMedico(this.medico).subscribe(
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
