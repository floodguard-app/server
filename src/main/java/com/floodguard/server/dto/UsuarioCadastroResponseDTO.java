package com.floodguard.server.dto;

import java.time.LocalDateTime;

public class UsuarioCadastroResponseDTO {
    // Atributos
    private Integer id;
    private LocalDateTime dataCriacao;
    private String email;
    private Integer idRegiao;

    // Construtores
    public UsuarioCadastroResponseDTO() {}

    public UsuarioCadastroResponseDTO(Integer id, LocalDateTime dataCriacao, String email, Integer idRegiao) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.email = email;
        this.idRegiao = idRegiao;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getIdRegiao() { return idRegiao; }
    public void setIdRegiao(Integer idRegiao) { this.idRegiao = idRegiao; }
}