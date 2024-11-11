package com.Backend.AppBanco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Backend.AppBanco.entity.ContaEntity;
import com.Backend.AppBanco.entity.TransacaoEntity;

public interface TransacaoRepository extends JpaRepository<TransacaoEntity, Long> {
    List<TransacaoEntity> findByConta(ContaEntity conta);
}
