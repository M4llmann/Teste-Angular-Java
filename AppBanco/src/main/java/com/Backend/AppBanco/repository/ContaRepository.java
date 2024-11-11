package com.Backend.AppBanco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Backend.AppBanco.entity.ContaEntity;

public interface ContaRepository extends JpaRepository<ContaEntity, Integer> {

    

}
