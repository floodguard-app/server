package com.floodguard.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.floodguard.server.service.PrevisaoService;
import com.floodguard.server.service.UsuarioService;
import com.floodguard.server.model.Usuario;

@RestController
@RequestMapping("/previsao")
public class PrevisaoController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PrevisaoService previsaoService;

    @GetMapping
    public ResponseEntity<?> obterPrevisao(@RequestParam double lat, @RequestParam double lon) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado");
        }

        String email = ((UserDetails) authentication.getPrincipal()).getUsername();
        Usuario usuario = usuarioService.buscarPorEmail(email);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }

        try {
            Object resultado = previsaoService.buscarPrevisao(lat, lon);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body("Erro ao acessar a API externa: " + e.getMessage());
        }
    }
}
