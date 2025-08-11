package com.floodguard.server.dto;

public class UsuarioLoginResponseDTO {
    // Atributos
    private UsuarioDTO usuario;
    private String token;

    // Construtores
    public UsuarioLoginResponseDTO(UsuarioDTO usuario, String token) {
        this.usuario = usuario;
        this.token = token;
    }

    // Getters e Setters
    public UsuarioDTO getUsuario() { return usuario; }
    public void setUsuario(UsuarioDTO usuario) { this.usuario = usuario; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
