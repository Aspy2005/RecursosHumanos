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
    
    
    
  }
