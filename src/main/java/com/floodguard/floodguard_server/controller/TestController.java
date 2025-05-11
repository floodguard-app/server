package com.floodguard.floodguard_server.controller;

import com.floodguard.floodguard_server.model.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public Message getTestMessage() {
        return new Message("Esta é uma mensagem padrão de teste.");
    }
}
