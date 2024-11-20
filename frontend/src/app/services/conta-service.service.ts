import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ContaDTO } from '../conta.model';  // Importando a interface

@Injectable({
  providedIn: 'root',
})
export class ContaService {
  private apiUrl = 'http://localhost:8080/api/contas'; // URL base da API

  constructor(private http: HttpClient) {}

  criarConta(contaDTO: { nomeTitular: string; status: string }): Observable<ContaDTO> {
    const url = `http://localhost:8080/api/contas`;
    return this.http.post<ContaDTO>(url, contaDTO); // Tipando a resposta como ContaDTO
  }
  
  

  realizarDeposito(idConta: number, valor: number) {
    const url = `http://localhost:8080/api/contas/${idConta}/deposito`;
    return this.http.post(url, valor);  // Enviando apenas o valor
  }
  
  
  realizarSaque(idConta: number, valor: number) {
    const url = `http://localhost:8080/api/contas/${idConta}/saque`;
    return this.http.post(url, valor);  // Enviar o valor do saque diretamente
  }

  getSaldo(idConta: number): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/${idConta}/saldo`);
  }

  getExtrato(idConta: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/${idConta}/extrato`);
  }
}
