package com.floodguard.floodguard_server.dto;

public class UsuarioDTO {
    private Long id;
    private String nomeUsuario;
    private String email;
    private Long idBairro;

    // Construtores
    public UsuarioDTO() {}

    public UsuarioDTO(Long id, String nomeUsuario, String email) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
    }

    public UsuarioDTO(Long id, String nomeUsuario, String email, Long idBairro) {
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

    public Long getIdBairro() { return idBairro; }
    public void setIdBairro(Long idBairro) { this.idBairro = idBairro; }
}