package com.floodguard.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.floodguard.server.model.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {
}