package com.floodguard.floodguard_server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.floodguard.floodguard_server.model.Advertencia;

@Repository
public interface AdvertenciaRepository extends JpaRepository<Advertencia, Long> {
    List<Advertencia> findByUsuarioComumId(Long usuarioId);
    List<Advertencia> findByAdministradorId(Long administradorId);
}
