package com.floodguard.server.dto;

public class CepUpdateDTO {
    private String cep;

    // Construtor vazio
    public CepUpdateDTO() {}

    // Construtor com parâmetro
    public CepUpdateDTO(String cep) {
        this.cep = cep;
    }

    // Getter e Setter
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}