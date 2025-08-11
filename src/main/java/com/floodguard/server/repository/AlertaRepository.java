package com.floodguard.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.floodguard.server.model.Alerta;

public interface AlertaRepository extends JpaRepository<Alerta, Integer> {
}