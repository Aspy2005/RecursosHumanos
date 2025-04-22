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

      // Prohibir acceso a las rutas de actualizar-datos y asistencia a RH
      if ((state.url.includes('actualizar-datos') || state.url.includes('asistencia')) && rol !== 'medico') {
        this.router.navigate(['/']);
        return false;
      }

      // Prohibir acceso a registrar-medico si no es RH
      if (state.url.includes('registrar-medico') && rol !== 'rh') {
        this.router.navigate(['/']);
        return false;
      }

      return true;
    }

    this.router.navigate(['/']);
    return false;
  }
}
