package com.floodguard.floodguard_server.dto;

public class UsuarioDTO {
    private Integer id;
    private String nomeUsuario;
    private String email;
    private Integer idBairro;

    // Construtores
    public UsuarioDTO(Integer id, String nomeUsuario, String email) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
    }

    public UsuarioDTO(Integer id, String nomeUsuario, String email, Integer idBairro) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
        this.idBairro = idBairro;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNomeUsuario() { return nomeUsuario; }
    public void setNomeUsuario(String nomeUsuario) { this.nomeUsuario = nomeUsuario; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getIdBairro() { return idBairro; }
    public void setIdBairro(Integer idBairro) { this.idBairro = idBairro; }
}