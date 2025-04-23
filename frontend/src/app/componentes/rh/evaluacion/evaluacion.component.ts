import { EvaluacionService } from './../../../servicios/evaluacion.service';
import { Component, OnInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MedicoDTO } from '../../../modelos/medico-dto';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-evaluacion-medicos',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, RouterModule, CommonModule],
  templateUrl: './evaluacion.component.html',
  styleUrls: ['./evaluacion.component.css']
})
export class EvaluacionComponent implements OnInit {

  medicos: MedicoDTO[] = [];
  medicoSeleccionado: MedicoDTO | null = null;

  registrosAsistencia: any[] = [];
  registrosPqr: any[] = [];

  registrosAsistenciaPaginados: any[] = [];
  registrosPqrPaginados: any[] = [];

  pagina = 0;
  elementosPorPagina = 5;

  filtroMes: number = new Date().getMonth() + 1;
  filtroAnio: number = new Date().getFullYear();

  calificacionAsistencia = 1;
  calificacionPqr = 1;
  observaciones = '';

  fechaHoy = new Date().toLocaleDateString();

  idPersonalRH: number = 0; // ← Se carga en ngOnInit desde el login

  meses = [
    { nombre: 'Enero', valor: 1 }, { nombre: 'Febrero', valor: 2 }, { nombre: 'Marzo', valor: 3 },
    { nombre: 'Abril', valor: 4 }, { nombre: 'Mayo', valor: 5 }, { nombre: 'Junio', valor: 6 },
    { nombre: 'Julio', valor: 7 }, { nombre: 'Agosto', valor: 8 }, { nombre: 'Septiembre', valor: 9 },
    { nombre: 'Octubre', valor: 10 }, { nombre: 'Noviembre', valor: 11 }, { nombre: 'Diciembre', valor: 12 }
  ];

  constructor(private EvaluacionService: EvaluacionService) {}

  ngOnInit(): void {
    const usuario = JSON.parse(localStorage.getItem('usuarioActual') || '{}');
this.idPersonalRH = usuario.id || 0;


    this.EvaluacionService.getMedicosConAsistenciaYPQR().subscribe(data => {
      this.medicos = data;
    }, err => {
      console.error("Error al obtener médicos:", err);
    });
  }

  seleccionarMedico(medico: MedicoDTO) {
    this.medicoSeleccionado = medico;
    this.obtenerRegistros();
  }

  obtenerRegistros() {
    if (!this.medicoSeleccionado) return;

    this.EvaluacionService.getAsistenciaYpqr(this.medicoSeleccionado.idMedico, this.filtroMes, this.filtroAnio)
      .subscribe(res => {
        this.registrosAsistencia = res.asistencias;
        this.registrosPqr = res.pqrs;
        this.actualizarPaginacion();
      });
  }

  filtrarRegistros() {
    this.pagina = 0;
    this.obtenerRegistros();
  }

  actualizarPaginacion() {
    this.registrosAsistenciaPaginados = this.registrosAsistencia.slice(this.pagina * this.elementosPorPagina, (this.pagina + 1) * this.elementosPorPagina);
    this.registrosPqrPaginados = this.registrosPqr.slice(this.pagina * this.elementosPorPagina, (this.pagina + 1) * this.elementosPorPagina);
  }

  paginaSiguiente() {
    this.pagina++;
    this.actualizarPaginacion();
  }

  paginaAnterior() {
    this.pagina--;
    this.actualizarPaginacion();
  }

  guardarEvaluacion() {
    if (!this.medicoSeleccionado) return;

    const puntajePromedio = Math.round((this.calificacionAsistencia + this.calificacionPqr) / 2);

    const evaluacion = {
      idMedicoEvaluado: this.medicoSeleccionado.idMedico,
      idPersonalRH: this.idPersonalRH,
      puntajeDesempeno: puntajePromedio,
      observaciones: this.observaciones
    };
    console.log(evaluacion);

    this.EvaluacionService.guardarEvaluacion(evaluacion).subscribe({
      next: () => {
        alert('Evaluación guardada correctamente');
        this.medicoSeleccionado = null;
      },
      error: (err) => {
        console.error('Error al guardar la evaluación:', err);
        alert('Error al guardar la evaluación. Verifica los datos.');
      }
    });
  }
}
