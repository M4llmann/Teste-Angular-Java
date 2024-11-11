package com.Backend.AppBanco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Backend.AppBanco.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>{

}
