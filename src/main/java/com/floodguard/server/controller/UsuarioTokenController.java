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
import com.floodguard.server.model.UsuarioToken;

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
}