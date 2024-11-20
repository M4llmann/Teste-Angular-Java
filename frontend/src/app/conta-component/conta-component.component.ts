import { Component, OnInit } from '@angular/core';
import { ContaService } from '../services/conta-service.service';
import { ContaDTO } from '../conta.model'; // Importando a interface

@Component({
  selector: 'app-conta',
  templateUrl: './conta-component.component.html',
  styleUrls: ['./conta-component.component.scss'],
})
export class ContaComponent implements OnInit {
  idConta: number | null = null;
  nomeTitular: string = '';
  saldo: number = 0;
  extrato: any[] = [];
  valor: number = 0;
  mostrarExtrato: boolean = false;

  constructor(private contaService: ContaService) {}

  ngOnInit(): void {
    if (this.idConta) {
      this.atualizarSaldo();
      this.atualizarExtrato();
    }
  }

  criarConta(): void {
    if (this.nomeTitular.trim() === '') {
      alert('Preencha o nome do titular para criar uma conta.');
      return;
    }

    const contaDTO = {
      nomeTitular: this.nomeTitular,
      status: 'true', // Garantir que status seja enviado como string
    };

    this.contaService.criarConta(contaDTO).subscribe({
      next: (conta: ContaDTO) => {  // Usando a interface ContaDTO para tipar a resposta
        this.idConta = conta.idConta;
        this.saldo = conta.saldo;
        alert('Conta criada com sucesso!');
        this.atualizarExtrato();
      },
      error: (err) => {
        console.error('Erro ao criar conta', err);
        alert('Erro ao criar conta. Tente novamente.');
      },
    });
  }
  

  realizarDeposito(): void {
    if (!this.idConta || this.valor <= 0) {
      alert('Informe um valor válido para depósito.');
      return;
    }
  
    // Enviar apenas o valor como número puro (não dentro de um objeto)
    this.contaService.realizarDeposito(this.idConta, this.valor).subscribe({
      next: () => {
        alert('Depósito realizado com sucesso!');
        this.atualizarSaldo();
        this.atualizarExtrato();
      },
      error: () => alert('Erro ao realizar depósito. Tente novamente.')
    });
  }

  realizarSaque(): void {
    if (!this.idConta || this.valor <= 0) {
      alert('Informe um valor válido para saque.');
      return;
    }
  
    // Enviar o valor diretamente (número simples)
    this.contaService.realizarSaque(this.idConta, this.valor).subscribe({
      next: () => {
        alert('Saque realizado com sucesso!');
        this.atualizarSaldo();
        this.atualizarExtrato();
      },
      error: (err) => {
        alert('Erro ao realizar saque. Tente novamente.');
        console.error(err);
      }
    });
  }

  private atualizarSaldo(): void {
    if (this.idConta) {
      this.contaService.getSaldo(this.idConta).subscribe({
        next: (saldo) => (this.saldo = saldo),
        error: (err) => console.error('Erro ao obter saldo:', err),
      });
    }
  }

  private atualizarExtrato(): void {
    if (this.idConta) {
      this.contaService.getExtrato(this.idConta).subscribe({
        next: (extrato) => (this.extrato = extrato),
        error: (err) => console.error('Erro ao obter extrato:', err),
      });
    }
  }
}
