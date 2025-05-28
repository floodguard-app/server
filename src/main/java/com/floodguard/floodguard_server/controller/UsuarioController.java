package com.floodguard.floodguard_server.controller;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
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

    private ResponseEntity<?> handleDatabaseExceptions(Exception e) {
        String message = "Erro ao processar a requisição";

        if (e.getCause() instanceof ConstraintViolationException) {
            message = "Violação de constraint: " + e.getCause().getMessage();
        }

        return ResponseEntity.badRequest().body(message);
    }
}
