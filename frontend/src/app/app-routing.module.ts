import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContaComponent } from './conta-component/conta-component.component';
import { OperacoesComponent } from './operacoes-component/operacoes-component.component';

const routes: Routes = [
  { path: '', component: ContaComponent },
  { path: 'operacoes', component: OperacoesComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
