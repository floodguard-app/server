package com.floodguard.floodguard_server.dto;

public class BairroDTO {
    private Long id;
    private String nomeBairro;

    public BairroDTO(Long id, String nomeBairro) {
        this.id = id;
        this.nomeBairro = nomeBairro;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeBairro() {
        return nomeBairro;
    }

    public void setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }
}