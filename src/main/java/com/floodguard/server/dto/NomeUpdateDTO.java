package com.floodguard.server.dto;

public class NomeUpdateDTO {
    private String nome;

    // Construtor vazio
    public NomeUpdateDTO() {}

    // Construtor com parâmetro
    public NomeUpdateDTO(String nome) {
        this.nome = nome;
    }

    // Getter e Setter
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
