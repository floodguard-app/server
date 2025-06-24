package com.floodguard.floodguard_server.controller;

import com.floodguard.floodguard_server.dto.ComentarioCreateDTO;
import com.floodguard.floodguard_server.dto.ComentarioResponseDTO;
import com.floodguard.floodguard_server.service.ComentarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @PostMapping
    public ResponseEntity<?> criarComentario(@Valid @RequestBody ComentarioCreateDTO dto) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado.");
            }

            // O email do usuário logado é o 'principal' no Spring Security
            String usuarioEmail = ((UserDetails) authentication.getPrincipal()).getUsername();

            ComentarioResponseDTO novoComentario = comentarioService.criarComentario(usuarioEmail, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoComentario);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar comentário: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<ComentarioResponseDTO>> listarComentarios() {
        try {
            List<ComentarioResponseDTO> comentarios = comentarioService.listarComentarios();
            return ResponseEntity.ok(comentarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Retorna 500 sem corpo detalhado em caso de erro geral
        }
    }
}