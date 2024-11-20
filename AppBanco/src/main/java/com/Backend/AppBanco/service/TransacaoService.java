package com.Backend.AppBanco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Backend.AppBanco.entity.TransacaoEntity;
import com.Backend.AppBanco.repository.TransacaoRepository;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    // Método para criar uma nova transação
    public TransacaoEntity criarTransacao(TransacaoEntity transacao) {
        return transacaoRepository.save(transacao);
    }

    // Método para buscar transações por idConta
    public List<TransacaoEntity> buscarTransacoesPorConta(Integer idConta) {
        return transacaoRepository.findByContaIdConta(idConta);
    }

    // Método para obter extrato de uma conta (todas as transações)
    public List<TransacaoEntity> obterExtrato(Integer idConta) {
        return transacaoRepository.findByContaIdConta(idConta);
    }

    
}
