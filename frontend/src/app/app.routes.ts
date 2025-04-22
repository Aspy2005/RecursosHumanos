import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from './guards/auth.guard';  // Importamos el guard
import { LoginComponent } from './componentes/login/login.component';
import { MenuMedicoComponent } from './componentes/menus/menu-medico/menu-medico.component';
import { MenuRhComponent } from './componentes/menus/menu-rh/menu-rh.component';
import { NgModule } from '@angular/core';
import { RegistrarMedicoComponent } from './componentes/rh/registrar-medico/registrar-medico.component';
import { ActualizarDatosComponent } from './componentes/medico/actualizar-datos/actualizar-datos.component';
import { AsistenciaComponent } from './componentes/medico/asistencia/asistencia.component'; // Asegúrate de tener este componente
import { SolicitudPermisoComponent } from './componentes/medico/solicitud-permiso/solicitud-permiso.component';

export const routes: Routes = [
  { path: '', component: LoginComponent },  // Ruta de login
  { path: 'menu-medico', component: MenuMedicoComponent, canActivate: [AuthGuard] },  // Ruta protegida para médicos
  { path: 'menu-rh', component: MenuRhComponent, canActivate: [AuthGuard] },  // Ruta protegida para RH
  { path: 'registrar-medico', component: RegistrarMedicoComponent, canActivate: [AuthGuard] },  // Ruta para registrar médicos
  { path: 'actualizar-datos', component: ActualizarDatosComponent, canActivate: [AuthGuard] },
  { path: 'asistencia', component: AsistenciaComponent, canActivate: [AuthGuard] },  // Ruta de asistencia protegida
  { path: 'solicitar-permiso', component: SolicitudPermisoComponent, canActivate: [AuthGuard] },  // Ruta de asistencia protegida


  { path: '**', redirectTo: '' }  // Redirección si la ruta no existe
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
