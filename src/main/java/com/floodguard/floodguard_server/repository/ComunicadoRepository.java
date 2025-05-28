package com.floodguard.floodguard_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.floodguard.floodguard_server.model.Comunicado;

@Repository
public interface ComunicadoRepository extends JpaRepository<Comunicado, Long> {
    
}
