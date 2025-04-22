import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-gestionar-solicitudes',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './gestionar-solicitudes.component.html',
  styleUrls: ['./gestionar-solicitudes.component.css']
})
export class GestionarSolicitudesComponent implements OnInit {
  solicitudes: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.cargarSolicitudes();
  }

  cargarSolicitudes(): void {
    this.http.get<any[]>('http://localhost:8080/api/solicitudes/pendientes').subscribe(data => {
      this.solicitudes = data;
    });
  }

  actualizarEstado(solicitudId: number, nuevoEstado: string): void {
    this.http.put(`http://localhost:8080/api/solicitudes/${solicitudId}/estado?estado=${nuevoEstado}`, null).subscribe({
      next: () => {
        this.solicitudes = this.solicitudes.filter(s => s.idPermiso !== solicitudId);
      },
      error: err => {
        console.error('Error al actualizar estado', err);
        alert('Hubo un error al actualizar la solicitud.');
      }
    });
  }
  
}
