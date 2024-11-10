package com.Backend.AppBanco.repository;

import com.Backend.AppBanco.model.Transacao;
import com.Backend.AppBanco.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByConta(Conta conta);
}
