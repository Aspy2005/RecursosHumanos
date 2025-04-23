import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MedicoDTO } from '../modelos/medico-dto';

@Injectable({
  providedIn: 'root'
})
export class EvaluacionService {

  private baseUrl = 'http://localhost:8080/api/evaluaciones'; // Asegúrate de que coincida con tu backend

  constructor(private http: HttpClient) {}

  // 1. Obtener médicos con registros de asistencia y PQR
  getMedicosConAsistenciaYPQR(): Observable<MedicoDTO[]> {
    return this.http.get<MedicoDTO[]>(`${this.baseUrl}/medicos`);
  }

  // 2. Obtener registros de asistencia y PQR por médico, mes y año
  getAsistenciaYpqr(idMedico: number, mes: number, anio: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/evaluacion/detalles?idMedico=${idMedico}&mes=${mes}&anio=${anio}`);
  }
  

  // 3. Guardar evaluación
  guardarEvaluacion(evaluacion: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/guardar`, evaluacion);  }
}
