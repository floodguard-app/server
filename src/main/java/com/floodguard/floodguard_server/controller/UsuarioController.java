package com.floodguard.floodguard_server.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.floodguard.floodguard_server.model.Usuario;
import com.floodguard.floodguard_server.dto.UsuarioCadastroDTO;
import com.floodguard.floodguard_server.dto.UsuarioDTO;
import com.floodguard.floodguard_server.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService service;
    private final PasswordEncoder encoder;

    @Autowired
    public UsuarioController(UsuarioService service, PasswordEncoder encoder) {
        this.service = service;
        this.encoder = encoder;
    }

    @PostMapping
    public UsuarioDTO cadastrar(@RequestBody UsuarioCadastroDTO dto) {
        return service.cadastrar(dto, encoder);
    }

    @GetMapping
    public List<UsuarioDTO> listar() {
        return service.listarTodos();
    }
}
