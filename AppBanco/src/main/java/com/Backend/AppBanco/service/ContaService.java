package com.Backend.AppBanco.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Backend.AppBanco.dto.ContaDTO;
import com.Backend.AppBanco.entity.ContaEntity;
import com.Backend.AppBanco.repository.ContaRepository;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public ContaEntity criarConta(ContaDTO contaDTO) {
        ContaEntity novaConta = new ContaEntity();
        novaConta.setNomeTitular(contaDTO.getNomeTitular());
        novaConta.setSaldo(BigDecimal.ZERO);  // Inicializa com saldo zero
        novaConta.setStatus(true);  // Conta criada como ativa
        novaConta.setDataCriacao(LocalDateTime.now());
        return contaRepository.save(novaConta);
    }

    public BigDecimal consultarSaldo(int idConta) {
        ContaEntity conta = contaRepository.findById(idConta)
            .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        return conta.getSaldo();
    }
}
