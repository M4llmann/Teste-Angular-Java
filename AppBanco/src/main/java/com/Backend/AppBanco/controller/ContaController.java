package com.Backend.AppBanco.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Backend.AppBanco.dto.ContaDTO;
import com.Backend.AppBanco.dto.ErroDTO;
import com.Backend.AppBanco.dto.TransacaoDTO;
import com.Backend.AppBanco.entity.ContaEntity;
import com.Backend.AppBanco.entity.TransacaoEntity;
import com.Backend.AppBanco.service.ContaService;
import com.Backend.AppBanco.service.TransacaoService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @Autowired
    private TransacaoService transacaoService;

    // Endpoint para criação de conta
    @PostMapping
    public ResponseEntity<?> criarConta(@RequestBody ContaDTO contaDTO) {
        try {
            ContaEntity conta = new ContaEntity();
            conta.setNomeTitular(contaDTO.getNomeTitular());
            conta.setStatus(contaDTO.getStatus());
            conta = contaService.criarConta(conta);
            return ResponseEntity.ok(new ContaDTO(conta.getIdConta(), conta.getNomeTitular(), conta.getSaldo(),
                    conta.getStatus(), conta.getDataCriacao()));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new ErroDTO("Erro ao criar conta"));
        }
    }

    // Exemplo de código ajustado no ContaController para o método de depósito
@PostMapping("/{idConta}/deposito")
public ResponseEntity<?> realizarDeposito(@PathVariable Integer idConta, @RequestBody BigDecimal valor) {
    try {
        ContaEntity conta = contaService.atualizarSaldo(idConta, valor);

        // Cria e salva a transação de depósito
        TransacaoEntity transacao = new TransacaoEntity("DEPOSITO", valor, conta);
        transacaoService.criarTransacao(transacao);

        return ResponseEntity.ok(new ContaDTO(conta.getIdConta(), conta.getNomeTitular(), conta.getSaldo(),
                conta.getStatus(), conta.getDataCriacao()));
    } catch (Exception e) {
        return ResponseEntity.status(404).body(new ErroDTO("Conta não encontrada"));
    }
}

// Exemplo de código ajustado no ContaController para o método de saque
@PostMapping("/{idConta}/saque")
    public ResponseEntity<?> realizarSaque(@PathVariable Integer idConta, @RequestBody BigDecimal valor) {
        try {
            // Buscar a conta
            ContaEntity conta = contaService.buscarContaPorId(idConta);

            // Verificar se o saldo é suficiente para o saque
            if (conta.getSaldo().compareTo(valor) < 0) {
                return ResponseEntity.status(400).body(new ErroDTO("Saldo insuficiente para realizar o saque"));
            }

            // Atualizar o saldo da conta (subtrair valor)
            conta = contaService.atualizarSaldo(idConta, valor.negate());

            // Criar e salvar a transação de saque
            TransacaoEntity transacao = new TransacaoEntity("SAQUE", valor.negate(), conta);
            transacaoService.criarTransacao(transacao);

            // Retornar a resposta com os dados da conta atualizada
            return ResponseEntity.ok(new ContaDTO(conta.getIdConta(), conta.getNomeTitular(), conta.getSaldo(),
                    conta.getStatus(), conta.getDataCriacao()));

        } catch (Exception e) {
            return ResponseEntity.status(404).body(new ErroDTO("Conta não encontrada"));
        }
    }

    // Endpoint para consultar saldo
    @GetMapping("/{idConta}/saldo")
    public ResponseEntity<?> consultarSaldo(@PathVariable Integer idConta) {
        Optional<ContaEntity> contaOpt = contaService.consultarSaldo(idConta);
        if (contaOpt.isPresent()) {
            return ResponseEntity.ok(contaOpt.get().getSaldo());
        } else {
            return ResponseEntity.status(404).body(new ErroDTO("Conta não encontrada"));
        }
    }

    // Endpoint para obter o extrato da conta
    @GetMapping("/{idConta}/extrato")
    public ResponseEntity<?> obterExtrato(@PathVariable Integer idConta) {
        try {
            List<TransacaoDTO> extrato = transacaoService.obterExtrato(idConta).stream()
                .map(transacao -> new TransacaoDTO(transacao.getIdTransacao(), transacao.getConta().getIdConta(),
                        transacao.getTipo(), transacao.getValor(), transacao.getDataTransacao()))
                .collect(Collectors.toList());
            return ResponseEntity.ok(extrato);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(new ErroDTO("Conta não encontrada"));
        }
    }
    // Endpoint para listar todas as contas
    @GetMapping
    public ResponseEntity<?> listarContas() {
        try {
            List<ContaEntity> contas = contaService.listarContas();  // Chama o método do service
            List<ContaDTO> contasDTO = contas.stream()
                    .map(conta -> new ContaDTO(conta.getIdConta(), conta.getNomeTitular(), conta.getSaldo(),
                            conta.getStatus(), conta.getDataCriacao()))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(contasDTO);  // Retorna a lista de contas
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ErroDTO("Erro ao listar contas"));
        }
    }

    
}
