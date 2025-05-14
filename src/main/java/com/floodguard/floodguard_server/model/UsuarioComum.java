package com.floodguard.floodguard_server.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarioComum")
public class UsuarioComum {
    
    @Id
    @Column(name = "idUsuarioComum")
    private Integer id;

    // @OneToOne
    // @MapsId
    // @JoinColumn(name = "idUsuarioComum")
    // private Usuario usuario;

    public UsuarioComum() {}
    public UsuarioComum(Integer id) { this.id = id; }
    
    // public UsuarioComum(Usuario usuario) { this.usuario = usuario; }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    // public Usuario getUsuario() { return usuario; }
    // public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}