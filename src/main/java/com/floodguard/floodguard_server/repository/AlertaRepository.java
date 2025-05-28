package com.floodguard.floodguard_server.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.floodguard.floodguard_server.model.Alerta;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    List<Alerta> findByBairroId(Long bairroId);
    List<Alerta> findByHorarioEnvioBetween(LocalDateTime inicio, LocalDateTime fim);
}
