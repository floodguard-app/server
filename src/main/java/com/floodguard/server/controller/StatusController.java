package com.floodguard.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // <-- A anotação correta para endpoints REST
@RequestMapping("/api") // <-- O prefixo do seu endpoint
public class StatusController {
    
    @GetMapping("/status") // <-- O caminho específico do endpoint
    public ResponseEntity<String> getStatus() {
        return new ResponseEntity<>("Servidor está rodando.", HttpStatus.OK);
    }
}