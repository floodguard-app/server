package com.floodguard.floodguard_server.service;

import com.floodguard.floodguard_server.dto.ComentarioCreateDTO;
import com.floodguard.floodguard_server.dto.ComentarioResponseDTO;
import com.floodguard.floodguard_server.model.Comentario;
import com.floodguard.floodguard_server.model.Usuario;
import com.floodguard.floodguard_server.repository.ComentarioRepository;
import com.floodguard.floodguard_server.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final UsuarioRepository usuarioRepository;

    public ComentarioService(ComentarioRepository comentarioRepository, UsuarioRepository usuarioRepository) {
        this.comentarioRepository = comentarioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public ComentarioResponseDTO criarComentario(String usuarioEmail, ComentarioCreateDTO dto) {
        // Encontrar o usuário pelo email (extraído do token)
        Usuario usuario = usuarioRepository.findByEmail(usuarioEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + usuarioEmail));

        // Criar o objeto Comentario
        Comentario comentario = new Comentario(
                dto.getConteudo(),
                LocalDateTime.now(), // horarioEnvio é a data atual
                usuario // Associar o usuário
        );

        // idAlerta, idComunicado, idResposta são nulos por padrão no construtor
        // statusRestricao é false por padrão no construtor

        Comentario savedComentario = comentarioRepository.save(comentario);

        // Mapear para ComentarioResponseDTO
        return new ComentarioResponseDTO(
                savedComentario.getId(),
                savedComentario.getConteudo(),
                savedComentario.getHorarioEnvio(),
                savedComentario.getStatusRestricao(),
                savedComentario.getUsuario().getId(),
                savedComentario.getUsuario().getNomeUsuario()
        );
    }

    public List<ComentarioResponseDTO> listarComentarios() {
        List<Comentario> comentarios = comentarioRepository.findAllByOrderByHorarioEnvioDesc(); // Ordenado!

        return comentarios.stream()
                .map(comentario -> new ComentarioResponseDTO(
                        comentario.getId(),
                        comentario.getConteudo(),
                        comentario.getHorarioEnvio(),
                        comentario.getStatusRestricao(),
                        comentario.getUsuario() != null ? comentario.getUsuario().getId() : null, // Evitar NullPointerException
                        comentario.getUsuario() != null ? comentario.getUsuario().getNomeUsuario() : null
                ))
                .collect(Collectors.toList());
    }
}