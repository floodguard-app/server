package com.floodguard.floodguard_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.floodguard.floodguard_server.model.UsuarioComum;

@Repository
public interface UsuarioComumRepository extends JpaRepository<UsuarioComum, Long> {
    boolean existsByUsuarioId(Long usuarioId);
}
