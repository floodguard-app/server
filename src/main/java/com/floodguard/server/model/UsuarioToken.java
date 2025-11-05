package com.floodguard.server.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "usuario_token")
public class UsuarioToken {

    @EmbeddedId
    private UsuarioTokenId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    public UsuarioToken() {}

    public UsuarioToken(Usuario usuario, String token) {
        this.usuario = usuario;
        this.id = new UsuarioTokenId(usuario.getId(), token);
    }

    public UsuarioTokenId getId() {
        return id;
    }

    public void setId(UsuarioTokenId id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getToken() {
        return id != null ? id.getToken() : null;
    }

    public void setToken(String token) {
        if (this.id == null) {
            this.id = new UsuarioTokenId();
        }
        this.id.setToken(token);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioToken)) return false;
        UsuarioToken that = (UsuarioToken) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(usuario, that.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario);
    }

    @Override
    public String toString() {
        return "UsuarioToken{" +
               "id=" + id +
               ", usuario=" + (usuario != null ? usuario.getId() : "null") +
               '}';
    }

    // Classe da chave composta
    @Embeddable
    public static class UsuarioTokenId implements Serializable {
        
        @Column(name = "id_usuario")
        private Integer idUsuario;

        @Column(name = "token", length = 200)
        private String token;

        public UsuarioTokenId() {}

        public UsuarioTokenId(Integer idUsuario, String token) {
            this.idUsuario = idUsuario;
            this.token = token;
        }

        public Integer getIdUsuario() { 
            return idUsuario; 
        }
        
        public void setIdUsuario(Integer idUsuario) { 
            this.idUsuario = idUsuario; 
        }

        public String getToken() { 
            return token; 
        }
        
        public void setToken(String token) { 
            this.token = token; 
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof UsuarioTokenId)) return false;
            UsuarioTokenId that = (UsuarioTokenId) o;
            return Objects.equals(idUsuario, that.idUsuario) &&
                   Objects.equals(token, that.token);
        }

        @Override
        public int hashCode() {
            return Objects.hash(idUsuario, token);
        }

        @Override
        public String toString() {
            return "UsuarioTokenId{" +
                   "idUsuario=" + idUsuario +
                   ", token='" + token + '\'' +
                   '}';
        }
    }
}