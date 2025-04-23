import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ReporteService {
  private baseUrl = 'http://localhost:8080/api/reportes';

  constructor(private http: HttpClient) {}

  validarMedicoPorCedula(cedula: string): Observable<boolean> {
    return this.http.get<boolean>(`${this.baseUrl}/validar/${cedula}`);
  }

  obtenerReporte(cedula: string, tipo: string): Observable<any> {
    const tipoFormateado = tipo.toLowerCase() === 'desempeño' ? 'desempeno' : tipo.toLowerCase();
    return this.http.get<any>(`${this.baseUrl}/${cedula}/${tipoFormateado}`);
  }
  
  descargarReportePDF(cedula: string, tipo: string): Observable<Blob> {
    const tipoFormateado = tipo.toLowerCase() === 'desempeño' ? 'desempeno' : tipo.toLowerCase();
    const url = `${this.baseUrl}/pdf/${cedula}/${tipoFormateado}`;
    return this.http.get(url, { responseType: 'blob' });
  }
  
  
  
  
}
