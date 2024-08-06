import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CanalListComponent } from './canal-list/canal-list.component';
import { CanalFormComponent } from './canal-form/canal-form.component';

const routes: Routes = [
  { path: 'canal-list', component: CanalListComponent },
  { path: 'canal-form/:id', component: CanalFormComponent }, // Para editar
  { path: 'canal-form', component: CanalFormComponent }, // Para adicionar
  { path: '', redirectTo: '/canal-list', pathMatch: 'full' },
  { path: '**', redirectTo: '/canal-list' }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
