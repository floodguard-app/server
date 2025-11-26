package com.floodguard.server.dto;

public class UsuarioTokenUpdateDTO {
    private String token; // O push token
    private Boolean receberNotificacoes;

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Boolean getReceberNotificacoes() { return receberNotificacoes; }
    public void setReceberNotificacoes(Boolean receberNotificacoes) { this.receberNotificacoes = receberNotificacoes; }
}