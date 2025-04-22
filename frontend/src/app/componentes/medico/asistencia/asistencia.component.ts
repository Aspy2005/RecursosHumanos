import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { AsistenciaService } from './../../../servicios/asistencia.service';
import { LoginService } from './../../../servicios/login.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-asistencia',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule, ReactiveFormsModule, RouterModule],
  templateUrl: './asistencia.component.html',
  styleUrls: ['./asistencia.component.css']
})
export class AsistenciaComponent implements OnInit {
  asistenciaForm!: FormGroup;
  entradaRegistrada: boolean = false;
  salidaRegistrada: boolean = false;
  idMedico!: number;

  constructor(
    private fb: FormBuilder,
    private AsistenciaService: AsistenciaService,
    private LoginService: LoginService
  ) {}

  ngOnInit(): void {
    this.idMedico = this.LoginService.obtenerUsuario().id;
    this.asistenciaForm = this.fb.group({});
    this.verificarRegistro();
  }

  verificarRegistro() {
    if (!this.idMedico) {
      window.alert('ID de médico no encontrado');
      return;
    }

    this.AsistenciaService.verificarAsistencia(this.idMedico).subscribe(
      (response) => {
        this.entradaRegistrada = response.entradaRegistrada;
        this.salidaRegistrada = response.salidaRegistrada;
      },
      () => {
        window.alert('Error al verificar el registro de asistencia');
      }
    );
  }

  registrarEntrada() {
    if (!this.idMedico) {
      window.alert('ID de médico no encontrado');
      return;
    }

    if (this.entradaRegistrada) {
      window.alert('¡La hora de entrada ya fue registrada!');
      return;
    }

    this.AsistenciaService.registrarEntrada(this.idMedico).subscribe(
      (response) => {
        this.entradaRegistrada = true;
        window.alert(response.mensaje || 'Hora de entrada registrada correctamente');
      },
      (error) => {
        console.error('Error en registrarEntrada:', error);
        window.alert(error.error?.error || 'Error al registrar la hora de entrada');
      }
    );
  }

  registrarSalida() {
    if (!this.idMedico) {
      window.alert('ID de médico no encontrado');
      return;
    }
  
    if (!this.entradaRegistrada) {
      window.alert('Error: NO se puede registrar la salida sin haber registrado la entrada');
      return;
    }
  
    if (this.salidaRegistrada) {
      window.alert('¡La hora de salida ya fue registrada!');
      return;
    }
  
    this.AsistenciaService.registrarSalida(this.idMedico).subscribe(
      (response) => {
        this.salidaRegistrada = true;
        window.alert(response.mensaje); // ← muestra horas trabajadas
      },
      (error) => {
        window.alert(error.error?.error || 'Error al registrar la hora de salida');
      }
    );
  }
  

  borrarRegistro() {
    if (!this.idMedico) {
      window.alert('ID de médico no encontrado');
      return;
    }

    this.AsistenciaService.borrarRegistro(this.idMedico).subscribe(
      (response) => {
        this.entradaRegistrada = false;
        this.salidaRegistrada = false;
        window.alert(response.mensaje || 'Registro borrado exitosamente');
      },
      (error) => {
        window.alert(error.error?.error || 'Error al borrar el registro');
      }
    );
  }

  onSubmit() {}
}
