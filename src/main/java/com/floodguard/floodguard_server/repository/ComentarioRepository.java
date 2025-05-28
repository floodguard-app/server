package com.floodguard.floodguard_server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.floodguard.floodguard_server.model.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByAlertaId(Long alertaId);
    List<Comentario> findByComunicadoId(Long comunicadoId);
    List<Comentario> findByStatusRestricao(Boolean statusRestricao);
}
