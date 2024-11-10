package com.Backend.AppBanco.service;

import com.Backend.AppBanco.model.Conta;
import com.Backend.AppBanco.repository.ContaRepository;
import com.Backend.AppBanco.dto.ContaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public Conta criarConta(ContaDTO contaDTO) {
        Conta novaConta = new Conta();
        novaConta.setNomeTitular(contaDTO.getNomeTitular());
        novaConta.setSaldo(BigDecimal.ZERO);  // Inicializa com saldo zero
        novaConta.setStatus(true);  // Conta criada como ativa
        novaConta.setDataCriacao(LocalDateTime.now());
        return contaRepository.save(novaConta);
    }

    public BigDecimal consultarSaldo(int idConta) {
        Conta conta = contaRepository.findById(idConta)
            .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        return conta.getSaldo();
    }
}
