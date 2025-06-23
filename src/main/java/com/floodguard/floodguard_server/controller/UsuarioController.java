package com.floodguard.floodguard_server.controller;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.floodguard.floodguard_server.dto.UsuarioCadastroDTO;
import com.floodguard.floodguard_server.dto.UsuarioDTO;
import com.floodguard.floodguard_server.dto.UsuarioLoginDTO;
import com.floodguard.floodguard_server.dto.UsuarioLoginResponseDTO;
import com.floodguard.floodguard_server.exception.EmailAlreadyExistsException;
import com.floodguard.floodguard_server.exception.UsernameAlreadyExistsException;
import com.floodguard.floodguard_server.model.Usuario;
import com.floodguard.floodguard_server.model.UsuarioComum;
import com.floodguard.floodguard_server.service.UsuarioService;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/comum/cadastro")
    public ResponseEntity<?> criarUsuarioComum(@Valid @RequestBody UsuarioCadastroDTO dto) {
        try {
            UsuarioComum usuarioComum = usuarioService.criarUsuarioComum(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioComum);
        } catch (EmailAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (UsernameAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            return handleDatabaseExceptions(e);
        }
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioLoginDTO dto) {
        try {
            UsuarioLoginResponseDTO response = usuarioService.autenticarUsuario(dto);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PatchMapping("/nomeUsuario")
    public ResponseEntity<?> atualizarNomeUsuario(@RequestBody String novoNomeUsuario) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado");
            }

            // O principal geralmente é o UserDetails, que contém o email (username para Spring Security)
            String email = ((UserDetails) authentication.getPrincipal()).getUsername();

            UsuarioDTO updatedUser = usuarioService.atualizarNomeUsuario(email, novoNomeUsuario);
            return ResponseEntity.ok(updatedUser);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar nome de usuario: " + e.getMessage());
        }
    }

    private ResponseEntity<?> handleDatabaseExceptions(Exception e) {
        String message = "Erro ao processar a requisição";

        if (e.getCause() instanceof ConstraintViolationException) {
            message = "Violação de constraint: " + e.getCause().getMessage();
        }

        return ResponseEntity.badRequest().body(message);
    }
}
