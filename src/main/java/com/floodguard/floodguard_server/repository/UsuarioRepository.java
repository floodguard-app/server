package com.floodguard.floodguard_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.floodguard.floodguard_server.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}