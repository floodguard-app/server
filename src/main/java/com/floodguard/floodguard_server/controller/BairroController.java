package com.floodguard.floodguard_server.controller;

import com.floodguard.floodguard_server.dto.BairroDTO;
import com.floodguard.floodguard_server.service.BairroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bairros")
public class BairroController {
    private final BairroService bairroService;

    public BairroController(BairroService bairroService) {
        this.bairroService = bairroService;
    }

    @GetMapping
    public ResponseEntity<List<BairroDTO>> listarTodosOsBairros() {
        List<BairroDTO> bairros = bairroService.listarBairros();
        return ResponseEntity.ok(bairros);
    }
}