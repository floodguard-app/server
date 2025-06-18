package com.floodguard.floodguard_server.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.floodguard.floodguard_server.dto.UsuarioCadastroDTO;
import com.floodguard.floodguard_server.dto.UsuarioDTO;
import com.floodguard.floodguard_server.dto.UsuarioLoginDTO;
import com.floodguard.floodguard_server.dto.UsuarioLoginResponseDTO;
import com.floodguard.floodguard_server.exception.EmailAlreadyExistsException;
import com.floodguard.floodguard_server.exception.UsernameAlreadyExistsException;
import com.floodguard.floodguard_server.model.Usuario;
import com.floodguard.floodguard_server.model.UsuarioComum;
import com.floodguard.floodguard_server.repository.UsuarioComumRepository;
import com.floodguard.floodguard_server.repository.UsuarioRepository;
import com.floodguard.floodguard_server.security.JwtUtil;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioComumRepository usuarioComumRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioComumRepository usuarioComumRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioComumRepository = usuarioComumRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public UsuarioComum criarUsuarioComum(UsuarioCadastroDTO dto) {
        // Verifica se email já existe
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException("Email já está em uso");
        }

        // Verifica se username já existe
        // if (usuarioRepository.existsByNomeUsuario(dto.getUsername())) {
        //     throw new UsernameAlreadyExistsException("Username já está em uso");
        // }

        // 1. Criação do usuário base
        Usuario usuario = new Usuario(
            "default_user", // dto.getUsername(),
            dto.getEmail(),
            passwordEncoder.encode(dto.getPassword()),
            LocalDateTime.now()
        );

        // Salva o usuário base
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        // 2. Criação do registro na tabela usuarioComum
        UsuarioComum usuarioComum = new UsuarioComum(usuarioSalvo);

        return usuarioComumRepository.save(usuarioComum);
        // return usuarioRepository.save(usuario);
    }

    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
            .map(usuario -> new UsuarioDTO(usuario.getId(), usuario.getNomeUsuario(), usuario.getEmail()))
            .collect(Collectors.toList());
    }

    public UsuarioLoginResponseDTO autenticarUsuario(UsuarioLoginDTO dto) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(dto.getEmail());
        if (usuarioOptional.isPresent()) {
            System.out.println("\n\n\n\n\nExtists!\n\n\n\n\n");
            Usuario usuario = usuarioOptional.get();
            if (passwordEncoder.matches(dto.getPassword(), usuario.getSenha())) {
                System.out.println("\n\n\n\n\nMatches!\n\n\n\n\n");
                String token = jwtUtil.generateToken(usuario.getEmail()); // Gera Token
                UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getId(), usuario.getNomeUsuario(), usuario.getEmail());
                return new UsuarioLoginResponseDTO(usuarioDTO, token);
            }
        }
        throw new BadCredentialsException("Email ou senha incorretos");
    }
}