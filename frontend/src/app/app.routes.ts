import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from './guards/auth.guard';

import { LoginComponent } from './componentes/login/login.component';
import { MenuMedicoComponent } from './componentes/menus/menu-medico/menu-medico.component';
import { MenuRhComponent } from './componentes/menus/menu-rh/menu-rh.component';

import { RegistrarMedicoComponent } from './componentes/rh/registrar-medico/registrar-medico.component';
import { GestionarSolicitudesComponent } from './componentes/rh/gestionar-solicitudes/gestionar-solicitudes.component';

import { ActualizarDatosComponent } from './componentes/medico/actualizar-datos/actualizar-datos.component';
import { AsistenciaComponent } from './componentes/medico/asistencia/asistencia.component';
import { SolicitudPermisoComponent } from './componentes/medico/solicitud-permiso/solicitud-permiso.component';
import { GestionarEmpleadoComponent } from './componentes/rh/gestionar-empleado/gestionar-empleado.component';
import { EvaluacionComponent } from './componentes/rh/evaluacion/evaluacion.component';
import { ReporteMedicoComponent } from './componentes/rh/reporte-medico/reporte-medico.component';

export const routes: Routes = [
  { path: '', component: LoginComponent },

  // Rutas para médicos
  { path: 'menu-medico', component: MenuMedicoComponent, canActivate: [AuthGuard] },
  { path: 'actualizar-datos', component: ActualizarDatosComponent, canActivate: [AuthGuard] },
  { path: 'asistencia', component: AsistenciaComponent, canActivate: [AuthGuard] },
  { path: 'solicitar-permiso', component: SolicitudPermisoComponent, canActivate: [AuthGuard] },

  // Rutas para RH
  { path: 'menu-rh', component: MenuRhComponent, canActivate: [AuthGuard] },
  { path: 'registrar-medico', component: RegistrarMedicoComponent, canActivate: [AuthGuard] },
  { path: 'gestionar-solicitudes', component: GestionarSolicitudesComponent, canActivate: [AuthGuard] },
  { path: 'gestor-empleado', component: GestionarEmpleadoComponent, canActivate: [AuthGuard] },
  { path: 'evaluar-empleado', component: EvaluacionComponent, canActivate: [AuthGuard] },
  { path: 'generar-reporte', component: ReporteMedicoComponent, canActivate: [AuthGuard] },



  // Ruta wildcard para redirigir a login si no existe
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
