package com.floodguard.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.floodguard.server.model.Analise;

public interface AnaliseRepository extends JpaRepository<Analise, Integer> {
}