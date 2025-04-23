import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ReporteService } from './../../../servicios/reporte.service';

@Component({
  selector: 'app-reporte-medico',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule, FormsModule],
  templateUrl: './reporte-medico.component.html',
  styleUrls: ['./reporte-medico.component.css']
})
export class ReporteMedicoComponent implements OnInit {

  reporteForm!: FormGroup;
  mensajeError: string = '';
  opcionesReporte: string[] = ['Asistencia', 'Desempeño', 'Permisos'];
  tipoSeleccionado: string = '';
  mostrarOpciones: boolean = false;
  datosReporte: any[] = [];
  mostrarReporte: boolean = false;

  columnasPorTipo: { [key: string]: string[] } = {
    Asistencia: ['fecha', 'horaEntrada', 'horaSalida'],
    Desempeño: ['fecha', 'calificacion', 'observaciones'],
    Permisos: ['fechaInicio', 'fechaFin', 'motivo', 'estado']
  };

  constructor(
    private fb: FormBuilder,
    private ReporteService: ReporteService
  ) {}

  ngOnInit(): void {
    this.reporteForm = this.fb.group({
      cedula: ['', Validators.required]
    });
  }

  buscarMedico(): void {
    const cedula = this.reporteForm.value.cedula;
    this.ReporteService.validarMedicoPorCedula(cedula).subscribe(
      existe => {
        if (existe) {
          this.mensajeError = '';
          this.mostrarOpciones = true;
          this.tipoSeleccionado = '';
          this.mostrarReporte = false;
        } else {
          this.mensajeError = 'No se encontró un médico con esa cédula.';
          this.mostrarOpciones = false;
          this.mostrarReporte = false;
        }
      },
      error => {
        console.error('Error al validar médico:', error);
        this.mensajeError = 'Error al validar la cédula del médico.';
        this.mostrarOpciones = false;
        this.mostrarReporte = false;
      }
    );
  }

  confirmarSeleccion(): void {
    if (!this.tipoSeleccionado) {
      this.mensajeError = 'Seleccione al menos un tipo de reporte.';
      this.mostrarReporte = false;
      return;
    }
  
    const cedula = this.reporteForm.value.cedula;
    this.ReporteService.obtenerReporte(cedula, this.tipoSeleccionado).subscribe(
      data => {
        console.log('Datos recibidos:', data);
  
        let clave = '';
        switch (this.tipoSeleccionado) {
          case 'Asistencia':
            clave = 'asistencias';
            break;
          case 'Desempeño':
            clave = 'evaluaciones';
            break;
          case 'Permisos':
            clave = 'permisos';
            break;
        }
  
        const lista = data[clave];
        if (Array.isArray(lista) && lista.length > 0) {
          this.datosReporte = lista;
          this.mensajeError = '';
          this.mostrarReporte = true;
          console.log('Datos que se mostrarán:', this.datosReporte);
        } else {
          this.mensajeError = `No se encontraron registros de ${this.tipoSeleccionado.toLowerCase()} para el médico.`;
          this.mostrarReporte = false;
        }
      },
      error => {
        console.error('Error al obtener el reporte:', error);
        this.mensajeError = 'Ocurrió un error al obtener el reporte.';
        this.mostrarReporte = false;
      }
    );
  }
  
  
    
  

  descargar(): void {
    const cedula = this.reporteForm.value.cedula;
    this.ReporteService.descargarReportePDF(cedula, this.tipoSeleccionado).subscribe(
      (pdfBlob) => {
        const blob = new Blob([pdfBlob], { type: 'application/pdf' });
        const url = window.URL.createObjectURL(blob);

        const link = document.createElement('a');
        link.href = url;
        link.download = `reporte-${this.tipoSeleccionado}-${cedula}.pdf`;
        link.click();

        window.URL.revokeObjectURL(url);
      },
      (error) => {
        this.mensajeError = 'Error al generar el PDF.';
        console.error(error);
      }
    );
  }

  esFecha(valor: any): boolean {
    return typeof valor === 'string' && (valor.includes('T') || !isNaN(Date.parse(valor)));
  }

  traducirColumna(col: string): string {
    const traducciones: any = {
      fecha: 'Fecha',
      horaEntrada: 'Hora Entrada',
      horaSalida: 'Hora Salida',
      calificacion: 'Calificación',
      observaciones: 'Observaciones',
      fechaInicio: 'Fecha Inicio',
      fechaFin: 'Fecha Fin',
      motivo: 'Motivo',
      estado: 'Estado'
    };
    return traducciones[col] || col;
  }
}
