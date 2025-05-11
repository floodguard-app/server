package com.floodguard.floodguard_server.dto;

public class UsuarioDTO {
    private Long id;
    private String nomeUsuario;
    private String email;
    private int idBairro;

    // Construtores
    public UsuarioDTO(Long id, String nomeUsuario, String email, int idBairro) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
        this.idBairro = idBairro;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeUsuario() { return nomeUsuario; }
    public void setNomeUsuario(String nomeUsuario) { this.nomeUsuario = nomeUsuario; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getIdBairro() { return idBairro; }
    public void setIdBairro(int idBairro) { this.idBairro = idBairro; }
}