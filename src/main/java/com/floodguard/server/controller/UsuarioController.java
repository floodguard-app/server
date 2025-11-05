package com.floodguard.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.floodguard.server.dto.CepUpdateDTO;
import com.floodguard.server.dto.RegiaoUpdateDTO;
import com.floodguard.server.dto.UsuarioCadastroDTO;
import com.floodguard.server.dto.UsuarioCadastroResponseDTO;
import com.floodguard.server.dto.UsuarioDTO;
import com.floodguard.server.dto.UsuarioLoginDTO;
import com.floodguard.server.dto.UsuarioLoginResponseDTO;
import com.floodguard.server.model.Usuario;
import com.floodguard.server.service.UsuarioService;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.floodguard.server.dto.CepUpdateDTO;;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> criarUsuarioComum(@Valid @RequestBody UsuarioCadastroDTO dto) {
        Usuario usuario = usuarioService.criarUsuario(dto);

        Integer idRegiao = (usuario.getRegiao() != null) ? usuario.getRegiao().getId() : null;

        UsuarioCadastroResponseDTO responseDTO = new UsuarioCadastroResponseDTO(
            usuario.getId(), 
            usuario.getDataCriacao(), 
            usuario.getEmail(), 
            idRegiao
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // @GetMapping
    // public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
    //     List<UsuarioDTO> usuarios = usuarioService.listarUsuarios();
    //     return ResponseEntity.ok(usuarios);
    // }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioLoginDTO dto) {
        UsuarioLoginResponseDTO response = usuarioService.autenticarUsuario(dto);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/regiao")
    public ResponseEntity<?> atualizarRegiaoUsuario(@RequestBody RegiaoUpdateDTO dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado");
        }

        String email = ((UserDetails) authentication.getPrincipal()).getUsername();

        UsuarioDTO updatedUser = usuarioService.atualizarRegiaoUsuario(email, dto.getIdRegiao());
        return ResponseEntity.ok(updatedUser);
    }

    @PatchMapping("/cep")
    public ResponseEntity<?> atualizarCepUsuario(@RequestBody CepUpdateDTO cepDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado");
        }

        String email = ((UserDetails) authentication.getPrincipal()).getUsername();

        UsuarioDTO updatedUser = usuarioService.atualizarCepUsuario(email, cepDTO.getCep());
        return ResponseEntity.ok(updatedUser);
    }
}