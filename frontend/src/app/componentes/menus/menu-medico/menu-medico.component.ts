import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router'; // ✅ Corrección aquí

@Component({
  selector: 'app-menu-medico',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './menu-medico.component.html',
  styleUrls: ['./menu-medico.component.css'],
})
export class MenuMedicoComponent {
  constructor(private _router: Router) {}

  cerrarSesion() {
    localStorage.removeItem('usuarioActual');
    this._router.navigate(['/']);
  }
}
