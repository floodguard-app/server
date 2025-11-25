package com.floodguard.server.dto;

public class UsuarioDTO {
    // Atributos
    private Integer id;
    private String email;
    private String cep;
    private String nome;

    // Construtores
    public UsuarioDTO() {}

    public UsuarioDTO(Integer id, String email) {
        this.id = id;
        this.email = email;
    }

    public UsuarioDTO(Integer id, String email, String cep, String nome) {
        this.id = id;
        this.email = email;
        this.cep = cep;
        this.nome = nome;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}