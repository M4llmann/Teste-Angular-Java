import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { ContaComponent } from './conta-component/conta-component.component';
import { OperacoesComponent } from './operacoes-component/operacoes-component.component';

@NgModule({
  declarations: [
    AppComponent,
    ContaComponent,
    OperacoesComponent // Registrado corretamente como um componente regular
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
