package com.floodguard.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.floodguard.server.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
}