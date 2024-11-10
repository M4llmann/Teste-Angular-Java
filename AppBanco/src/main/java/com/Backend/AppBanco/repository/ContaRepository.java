package com.Backend.AppBanco.repository;

import com.Backend.AppBanco.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Integer> {
}
