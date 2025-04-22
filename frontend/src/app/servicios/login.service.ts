import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface LoginRequest { // <-- debe estar exportado si se usa en otro lado
  usuario: string;
  contrasena: string;
}

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private apiUrl = 'http://localhost:8080/api/login';

  constructor(private http: HttpClient) {}

  autenticar(datos: LoginRequest): Observable<any> {
    return this.http.post(this.apiUrl, datos);
  }

  guardarUsuario(usuario: any): void {
    // Se asegura de que se guarde con la clave 'usuarioActual'
    localStorage.setItem('usuarioActual', JSON.stringify(usuario));
  }

  // Método en el LoginService
  obtenerUsuario(): any | null {
    const usuarioString = localStorage.getItem('usuarioActual');  // Usar la misma clave que en guardarUsuario

    if (!usuarioString) {
      console.warn('No hay usuario en localStorage');
      return null;
    }

    try {
      const usuario = JSON.parse(usuarioString);
      if (usuario && usuario.id && usuario.rol) {
        console.log('Usuario logueado:', usuario);
        return usuario;
      } else {
        console.warn('Usuario inválido o incompleto:', usuario);
        return null;
      }
    } catch (error) {
      console.error('Error al parsear usuario:', error);
      return null;
    }
  }
}
