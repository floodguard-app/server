package com.floodguard.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.floodguard.server.model.Usuario;
import com.floodguard.server.model.UsuarioToken;
import com.floodguard.server.model.UsuarioToken.UsuarioTokenId;
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

    public UsuarioToken atualizarNotificacoes(Integer idUsuario, String pushToken, Boolean status) {
        // Cria a chave composta
        UsuarioTokenId id = new UsuarioTokenId(idUsuario, pushToken);

        // Busca pela chave composta
        UsuarioToken usuarioToken = usuarioTokenRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Token não encontrado para este usuário."));

        // Atualiza o valor
        usuarioToken.setReceberNotificacoes(status);
        
        // Salva
        return usuarioTokenRepository.save(usuarioToken);
    }

    public Boolean consultarStatusNotificacoes(Integer idUsuario, String pushToken) {
        // 1. Cria a chave composta (ID do Usuário + Push Token)
        UsuarioTokenId id = new UsuarioTokenId(idUsuario, pushToken);

        // 2. Busca no banco
        UsuarioToken usuarioToken = usuarioTokenRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Token não encontrado para este usuário."));

        // 3. Retorna o valor do campo (true/false)
        return usuarioToken.getReceberNotificacoes();
    }
}
