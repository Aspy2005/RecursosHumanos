import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router'; // ✅ Corrección aquí

@Component({
  selector: 'app-menu-rh',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './menu-rh.component.html',
  styleUrls: ['./menu-rh.component.css'],
})
export class MenuRhComponent {
  constructor(private router: Router) {}

  cerrarSesion() {
    localStorage.removeItem('usuarioActual');
    this.router.navigate(['/']);
  }
}
