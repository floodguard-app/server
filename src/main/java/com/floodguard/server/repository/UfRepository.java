package com.floodguard.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.floodguard.server.model.Uf;

public interface UfRepository extends JpaRepository<Uf, Integer> {
}