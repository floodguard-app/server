package com.floodguard.server.dto;

public class UsuarioDTO {
    // Atributos
    private Integer id;
    private String email;
    private Integer idRegiao;

    // Construtores
    public UsuarioDTO() {}

    public UsuarioDTO(Integer id, String email) {
        this.id = id;
        this.email = email;
    }

    public UsuarioDTO(Integer id, String email, Integer idRegiao) {
        this.id = id;
        this.email = email;
        this.idRegiao = idRegiao;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getIdRegiao() { return idRegiao; }
    public void setIdRegiao(Integer idRegiao) { this.idRegiao = idRegiao; }
}