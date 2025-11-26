package com.floodguard.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.floodguard.server.model.Usuario;
import com.floodguard.server.model.UsuarioToken;
import com.floodguard.server.model.UsuarioToken.UsuarioTokenId;

public interface UsuarioTokenRepository extends JpaRepository<UsuarioToken, UsuarioTokenId> {
    void deleteByUsuario(Usuario usuario);
}