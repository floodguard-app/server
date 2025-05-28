package com.floodguard.floodguard_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.floodguard.floodguard_server.model.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    boolean existsByUsuarioId(Long usuarioId);
}
