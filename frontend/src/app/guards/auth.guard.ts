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

    if (!usuario) {
      this.router.navigate(['/']);
      return false;
    }

    const { rol } = JSON.parse(usuario);

    // Bloqueo por rutas específicas
    const url = state.url;

    // Acceso exclusivo a RH
    const soloRh = [
      'menu-rh',
      'registrar-medico',
      'gestionar-solicitudes'
    ];
    if (soloRh.some(r => url.includes(r)) && rol !== 'rh') {
      this.router.navigate(['/']);
      return false;
    }

    // Acceso exclusivo a médicos
    const soloMedico = [
      'menu-medico',
      'actualizar-datos',
      'asistencia'
    ];
    if (soloMedico.some(r => url.includes(r)) && rol !== 'medico') {
      this.router.navigate(['/']);
      return false;
    }

    return true;
  }
}
