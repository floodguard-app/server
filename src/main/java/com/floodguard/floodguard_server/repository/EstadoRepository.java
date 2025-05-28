package com.floodguard.floodguard_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.floodguard.floodguard_server.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
    Estado findByNomeEstado(String nomeEstado);
}
