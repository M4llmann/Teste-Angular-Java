package com.Backend.AppBanco.service;

import com.Backend.AppBanco.model.Conta;
import com.Backend.AppBanco.model.Transacao;
import com.Backend.AppBanco.repository.ContaRepository;
import com.Backend.AppBanco.repository.TransacaoRepository;
import com.Backend.AppBanco.dto.TransacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    public Transacao realizarDeposito(int idConta, TransacaoDTO transacaoDTO) {
        Conta conta = contaRepository.findById(idConta)
            .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        if (transacaoDTO.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do depósito deve ser maior que zero");
        }

        conta.depositar(transacaoDTO.getValor());
        contaRepository.save(conta);

        Transacao transacao = new Transacao();
        transacao.setConta(conta);
        transacao.setTipo("deposito");
        transacao.setValor(transacaoDTO.getValor());
        transacao.setDataTransacao(LocalDateTime.now());

        return transacaoRepository.save(transacao);
    }

    public Transacao realizarSaque(int idConta, TransacaoDTO transacaoDTO) {
        Conta conta = contaRepository.findById(idConta)
            .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        if (transacaoDTO.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser maior que zero");
        }

        if (conta.getSaldo().compareTo(transacaoDTO.getValor()) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para o saque");
        }

        conta.sacar(transacaoDTO.getValor());
        contaRepository.save(conta);

        Transacao transacao = new Transacao();
        transacao.setConta(conta);
        transacao.setTipo("saque");
        transacao.setValor(transacaoDTO.getValor());
        transacao.setDataTransacao(LocalDateTime.now());

        return transacaoRepository.save(transacao);
    }

    public List<Transacao> consultarExtrato(int idConta) {
        Conta conta = contaRepository.findById(idConta)
            .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        return transacaoRepository.findByConta(conta);
    }
}
