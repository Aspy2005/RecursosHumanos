// src/app/guards/auth.guard.ts
import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  Router
} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    const usuario = localStorage.getItem('usuarioActual');

    if (usuario) {
      const parsed = JSON.parse(usuario);
      const rol = parsed.rol;

      // Validamos el acceso por ruta
      if (state.url.includes('menu-rh') && rol !== 'rh') {
        this.router.navigate(['/']);
        return false;
      }

      if (state.url.includes('menu-medico') && rol !== 'medico') {
        this.router.navigate(['/']);
        return false;
      }

      if (state.url.includes('registrar-medico') && rol !== 'rh') {
        this.router.navigate(['/']);  // Los médicos no pueden acceder a la ruta de registrar-medico
        return false;
      }

      return true;
    }

    this.router.navigate(['/']);
    return false;
  }
}
