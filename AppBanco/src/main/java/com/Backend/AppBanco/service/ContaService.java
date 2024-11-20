package com.Backend.AppBanco.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Backend.AppBanco.entity.ContaEntity;
import com.Backend.AppBanco.repository.ContaRepository;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public ContaEntity criarConta(ContaEntity conta) {
        conta.setDataCriacao(LocalDateTime.now());
        conta.setSaldo(BigDecimal.ZERO);  // Inicializa o saldo como zero
        return contaRepository.save(conta);
    }

    public ContaEntity buscarContaPorId(Integer idConta) throws Exception {
        Optional<ContaEntity> contaOpt = contaRepository.findById(idConta);
        if (!contaOpt.isPresent()) {
            throw new Exception("Conta não encontrada");
        }
        return contaOpt.get();
    }

    public Optional<ContaEntity> consultarSaldo(Integer idConta) {
        return contaRepository.findById(idConta);
    }

    public ContaEntity atualizarSaldo(Integer idConta, BigDecimal valor) {
        ContaEntity conta = contaRepository.findById(idConta)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        conta.setSaldo(conta.getSaldo().add(valor));
        return contaRepository.save(conta);
    }
    public List<ContaEntity> listarContas() {
        return contaRepository.findAll();  // Retorna todas as contas do banco
    }
}
