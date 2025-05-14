package com.floodguard.floodguard_server.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.floodguard.floodguard_server.model.Usuario;
import com.floodguard.floodguard_server.model.UsuarioComum;
import com.floodguard.floodguard_server.dto.UsuarioCadastroDTO;
import com.floodguard.floodguard_server.dto.UsuarioDTO;
import com.floodguard.floodguard_server.repository.UsuarioComumRepository;
import com.floodguard.floodguard_server.repository.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;
    private final UsuarioComumService comumService;

    @Autowired
    public UsuarioService(UsuarioRepository repository, 
                         UsuarioComumService comumService) {
        this.repository = repository;
        this.comumService = comumService;
    }

    public List<UsuarioDTO> listarTodos() {
        List<Usuario> usuarios = repository.findAll();

        // Filtra os dados para retornar apenas os campos desejados (id, nomeUsuario, email)
        return usuarios.stream()
                .map(usuario -> new UsuarioDTO(usuario.getId(), usuario.getNomeUsuario(), usuario.getEmail(), usuario.getIdBairro()))
                .collect(Collectors.toList());
    }

    @Transactional
    public UsuarioDTO cadastrar(UsuarioCadastroDTO dto, PasswordEncoder encoder) {
        // Cria e salva o Usuario
        Usuario usuario = new Usuario();
        usuario.setNomeUsuario(dto.username);
        usuario.setEmail(dto.email);
        usuario.setSenha(encoder.encode(dto.password));
        usuario.setDataCriacao(LocalDateTime.now());
        
        Usuario salvo = repository.save(usuario);

        // Chama o service específico em uma nova transação
        // try {
        //     comumService.criarUsuarioComum(salvo.getId());
        // } catch (Exception e) {
        //     // Se falhar ao criar usuário comum, mantemos o usuário principal
        //     // mas você pode decidir remover o usuário aqui se quiser atomicidade completa
        //     throw new RuntimeException("Usuário criado, mas falha ao registrar como comum", e);
        // }

        return new UsuarioDTO(salvo.getId(), salvo.getNomeUsuario(), salvo.getEmail());
    }
}