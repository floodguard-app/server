package com.floodguard.floodguard_server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.floodguard.floodguard_server.model.Bairro;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Long> {
    List<Bairro> findByCidadeId(Long cidadeId);
    Bairro findByNomeBairro(String nomeBairro);
}
