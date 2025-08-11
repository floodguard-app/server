package com.floodguard.server.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.floodguard.server.dto.UsuarioCadastroDTO;
import com.floodguard.server.dto.UsuarioDTO;
import com.floodguard.server.dto.UsuarioLoginDTO;
import com.floodguard.server.dto.UsuarioLoginResponseDTO;
import com.floodguard.server.exception.EmailAlreadyExistsException;
import com.floodguard.server.model.Regiao;
import com.floodguard.server.model.Usuario;
import com.floodguard.server.repository.RegiaoRepository;
import com.floodguard.server.repository.UsuarioRepository;
import com.floodguard.server.security.JwtUtil;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RegiaoRepository regiaoRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, RegiaoRepository regiaoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.regiaoRepository = regiaoRepository;
    }

    public Usuario criarUsuario(UsuarioCadastroDTO dto) {
        // Verifica se email já existe
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException("Email já está em uso");
        }

        Usuario usuario = new Usuario(
            dto.getEmail(),
            passwordEncoder.encode(dto.getPassword())
        );

        return usuarioRepository.save(usuario); // Salva o usuário base
    }

    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
            .map(usuario -> new UsuarioDTO(usuario.getId(), usuario.getEmail()))
            .collect(Collectors.toList());
    }

    public UsuarioLoginResponseDTO autenticarUsuario(UsuarioLoginDTO dto) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(dto.getEmail());
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            if (passwordEncoder.matches(dto.getPassword(), usuario.getSenha())) {
                String token = jwtUtil.generateToken(usuario.getEmail()); // Gera Token
                UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getId(), usuario.getEmail());
                return new UsuarioLoginResponseDTO(usuarioDTO, token);
            }
        }
        throw new BadCredentialsException("Email ou senha incorretos");
    }

    public UsuarioDTO atualizarRegiaoUsuario(String email, Integer idRegiao) {
        // Verifica se usuário existe
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        if (usuarioOptional.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado.");
        }

        // Verifica se região existe
        Optional<Regiao> regiaoOptional = regiaoRepository.findById(idRegiao);
        if (regiaoOptional.isEmpty()) {
            throw new IllegalArgumentException("Região com ID " + idRegiao + " não encontrada.");
        }

        Usuario usuario = usuarioOptional.get();
        Regiao regiao = regiaoOptional.get();

        usuario.setRegiao(regiao); // Associa o região ao usuário
        Usuario updatedUser = usuarioRepository.save(usuario); // Salva o usuário atualizado

        // Mapeia o usuário atualizado para DTO, incluindo o novo idRegiao
        return new UsuarioDTO(updatedUser.getId(), updatedUser.getEmail(), updatedUser.getRegiao() != null ? updatedUser.getRegiao().getId() : null);
    }
}