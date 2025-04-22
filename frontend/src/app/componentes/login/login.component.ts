import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { LoginService } from './../../servicios/login.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  cedula: string = '';
  contrasena: string = '';
  error: string = '';

  constructor(
    private router: Router,
    private loginService: LoginService
  ) {}

  login() {
    if (!this.cedula || !this.contrasena) {
      this.error = 'Debe completar todos los campos.';
      return;
    }
  
    this.loginService.autenticar({ usuario: this.cedula, contrasena: this.contrasena })
      .subscribe({
        next: (res) => {
          console.log('Respuesta del servidor:', res);
  
          // Verificar si la autenticación fue exitosa
          if (res.autenticado) {
            // Guardamos el usuario con los datos de la respuesta
            this.loginService.guardarUsuario({
              id: res.id,
              rol: res.rol
            });
  
            // Verificar si el rol es 'medico' o 'rh' y redirigir
            if (res.rol?.toLowerCase() === 'medico') {
              this.router.navigate(['/menu-medico']);
            } else if (res.rol?.toLowerCase() === 'rh') {
              this.router.navigate(['/menu-rh']);
            } else {
              this.error = 'Rol no reconocido.';
              console.error('Rol no reconocido:', res.rol);
            }
          } else {
            this.error = 'Credenciales inválidas o error en la autenticación.';
          }
        },
        error: () => {
          this.error = 'Credenciales inválidas o error del servidor.';
        }
      });
  }
  
}  