package com.floodguard.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.floodguard.server.model.Regiao;

public interface RegiaoRepository extends JpaRepository<Regiao, Integer> {
}