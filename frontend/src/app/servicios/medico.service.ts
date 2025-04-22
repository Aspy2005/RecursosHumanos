  import { Injectable } from '@angular/core';
  import { HttpClient } from '@angular/common/http';
  import { Observable } from 'rxjs';
  import { Medico } from '../modelos/medico.model';

  @Injectable({
    providedIn: 'root'
  })
  export class MedicoService {
    private baseUrl = 'http://localhost:8080/api/medicos';

    constructor(private http: HttpClient) {}


     // Verificar si la cédula ya está registrada
  validarCedula(cedula: string): Observable<boolean> {
    return this.http.get<boolean>(`${this.baseUrl}/validar-cedula/${cedula}`);
  }
    // Registrar un nuevo médico
    registrarMedico(medico: Medico): Observable<Medico> {
      return this.http.post<Medico>(`${this.baseUrl}/registrar`, medico);
    }

    // Obtener datos de un médico por su ID
    obtenerDatosMedico(id: number): Observable<Medico> {
      return this.http.get<Medico>(`${this.baseUrl}/${id}`);
    }

    actualizarDatosMedico(id: number, medico: Medico): Observable<Medico> {
      return this.http.put<Medico>(`${this.baseUrl}/${id}`, medico);
    }
    
    obtenerMedicosActivos(): Observable<Medico[]> {
      return this.http.get<Medico[]>(`${this.baseUrl}/activos`);
    }
    
    inactivarMedico(id: number): Observable<any> {
      return this.http.put(`${this.baseUrl}/inactivar/${id}`, {});
    }
    reactivarMedico(id: number): Observable<any> {
      return this.http.put(`${this.baseUrl}/reactivar/${id}`, {});
    }
    
    
  }
