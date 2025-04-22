import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AsistenciaService {
  private apiUrl = 'http://localhost:8080/api/asistencia'; // Cambia la URL según tu backend

  constructor(private http: HttpClient) {}

  // Verificar asistencia
  verificarAsistencia(idMedico: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/verificar/${idMedico}`);
  }

  // Registrar entrada
  registrarEntrada(idMedico: number): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/entrada`, { idMedico });
  }

  // Registrar salida
  registrarSalida(idMedico: number): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/salida`, { idMedico });
  }

  // Borrar registro
  borrarRegistro(idMedico: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/borrar/${idMedico}`);
  }
}
