package com.floodguard.floodguard_server.model;

import jakarta.persistence.*; // se estiver usando Spring Boot 3+
import java.io.Serializable;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomeUsuario") 
    private String nomeUsuario;

    @Column(name = "senha")
    private String senha;

    @Column(name = "dataCriacao")
    private String dataCriacao;

    @Column(name = "idBairro")
    private Integer idBairro;

    private String email;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeUsuario() { return nomeUsuario; }
    public void setNomeUsuario(String nomeUsuario) { this.nomeUsuario = nomeUsuario; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    
    public String getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(String dataCriacao) { this.dataCriacao = dataCriacao; }

    public Integer getIdBairro() { return idBairro; }
    public void setIdBairro(Integer idBairro) { this.idBairro = idBairro; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
