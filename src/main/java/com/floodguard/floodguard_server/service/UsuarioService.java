package com.floodguard.floodguard_server.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.floodguard.floodguard_server.model.Usuario;
import com.floodguard.floodguard_server.dto.UsuarioDTO;
import com.floodguard.floodguard_server.repository.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<UsuarioDTO> listarTodos() {
        List<Usuario> usuarios = repository.findAll();

        // Filtra os dados para retornar apenas os campos desejados (id, nomeUsuario, email)
        return usuarios.stream()
                .map(usuario -> new UsuarioDTO(usuario.getId(), usuario.getNomeUsuario(), usuario.getEmail(), usuario.getIdBairro()))
                .collect(Collectors.toList());
    }
}