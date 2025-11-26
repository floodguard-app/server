package com.floodguard.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.floodguard.server.service.UsuarioService;
import com.floodguard.server.service.UsuarioTokenService;
import com.floodguard.server.dto.UsuarioTokenUpdateDTO;
import com.floodguard.server.model.UsuarioToken;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/token")
public class UsuarioTokenController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioTokenService usuarioTokenService;

    @PostMapping
    public ResponseEntity<?> salvarToken(@RequestParam String token) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado");
        }

        String email = ((UserDetails) authentication.getPrincipal()).getUsername();
        var usuario = usuarioService.buscarPorEmail(email);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }

        UsuarioToken salvo = usuarioTokenService.salvarToken(usuario.getId(), token);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PatchMapping
    public ResponseEntity<?> atualizarNotificacoes(@RequestBody UsuarioTokenUpdateDTO dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 1. Valida autenticação (Token de Login)
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado");
        }

        // 2. Recupera o usuário logado
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();
        var usuario = usuarioService.buscarPorEmail(email);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }

        try {
            // 3. Chama o serviço passando ID do usuário, o Push Token e o novo status
            UsuarioToken atualizado = usuarioTokenService.atualizarNotificacoes(
                usuario.getId(), 
                dto.getToken(), 
                dto.getReceberNotificacoes()
            );
            return ResponseEntity.ok(atualizado);
        } catch (jakarta.persistence.EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar token.");
        }
    }

    @GetMapping
    public ResponseEntity<?> obterStatusNotificacoes(@RequestParam String token) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 1. Valida autenticação (Token de Login/JWT)
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado");
        }

        // 2. Recupera o usuário logado a partir do JWT
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();
        var usuario = usuarioService.buscarPorEmail(email);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }

        try {
            // 3. Busca o status usando o ID do usuário (do JWT) e o Push Token (do parametro)
            Boolean recebeNotificacoes = usuarioTokenService.consultarStatusNotificacoes(
                usuario.getId(), 
                token
            );

            // 4. Retorna um JSON simples: { "receberNotificacoes": true }
            Map<String, Boolean> resposta = Collections.singletonMap("receberNotificacoes", recebeNotificacoes);
            
            return ResponseEntity.ok(resposta);

        } catch (jakarta.persistence.EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar status.");
        }
    }
}