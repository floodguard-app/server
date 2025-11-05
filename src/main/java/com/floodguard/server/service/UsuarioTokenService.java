package com.floodguard.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.floodguard.server.model.Usuario;
import com.floodguard.server.model.UsuarioToken;
import com.floodguard.server.repository.UsuarioRepository;
import com.floodguard.server.repository.UsuarioTokenRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioTokenService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioTokenRepository usuarioTokenRepository;

    public UsuarioToken salvarToken(Integer idUsuario, String token) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        UsuarioToken usuarioToken = new UsuarioToken(usuario, token);
        return usuarioTokenRepository.save(usuarioToken);
    }
}
