import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from './guards/auth.guard';  // Importamos el guard
import { LoginComponent } from './componentes/login/login.component';
import { MenuMedicoComponent } from './componentes/menus/menu-medico/menu-medico.component';
import { MenuRhComponent } from './componentes/menus/menu-rh/menu-rh.component';
import { NgModule } from '@angular/core';
import { RegistroMedicoComponent } from './componentes/rh/registrar-medico/registrar-medico.component';
import { ActualizarDatosComponent } from './componentes/medico/actualizar-datos/actualizar-datos.component';

export const routes: Routes = [
  { path: '', component: LoginComponent },  // Ruta de login
  { path: 'menu-medico', component: MenuMedicoComponent, canActivate: [AuthGuard] },  // Ruta protegida para médicos
  { path: 'menu-rh', component: MenuRhComponent, canActivate: [AuthGuard] },  // Ruta protegida para RH
  { path: 'registrar-medico', component: RegistroMedicoComponent, canActivate: [AuthGuard] },  // Ruta para registrar médicos
  { path: 'actualizar-datos', component: ActualizarDatosComponent, canActivate: [AuthGuard] },

  { path: '**', redirectTo: '' }  // Redirección si la ruta no existe
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
