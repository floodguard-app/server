package com.floodguard.floodguard_server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ComentarioCreateDTO {
    @NotBlank(message = "O conteúdo do comentário é obrigatório.")
    @Size(max = 1000, message = "O conteúdo do comentário não pode exceder 1000 caracteres.")
    private String conteudo;

    // Construtor padrão para desserialização
    public ComentarioCreateDTO() {}

    public ComentarioCreateDTO(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}