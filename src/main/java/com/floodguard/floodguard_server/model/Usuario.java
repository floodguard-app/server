package com.floodguard.floodguard_server.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nomeUsuario") 
    private String nomeUsuario;

    @Column(name = "senha")
    private String senha;

    @Column(name = "dataCriacao")
    private LocalDateTime dataCriacao;

    @Column(name = "idBairro")
    private Integer idBairro;

    @Column(unique = true)
    private String email;

    @Column(name = "funcao")
    private String funcao;

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNomeUsuario() { return nomeUsuario; }
    public void setNomeUsuario(String nomeUsuario) { this.nomeUsuario = nomeUsuario; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public Integer getIdBairro() { return idBairro; }
    public void setIdBairro(Integer idBairro) { this.idBairro = idBairro; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFuncao() { return funcao; }
    public void setFuncao(String funcao) { this.funcao = funcao; }

    // Implementação UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Retorna as permissões/roles do usuário
        return Arrays.stream(this.funcao.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email; // Usamos email como username para autenticação
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Conta nunca expira
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Credenciais nunca expiram
    }

    @Override
    public boolean isEnabled() {
        return true; // Conta sempre ativa
    }
}
