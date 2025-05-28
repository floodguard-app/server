package com.floodguard.floodguard_server.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comentario")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComentario")
    private Long id;

    private Boolean statusRestricao;

    private LocalDateTime horarioEnvio;

    @ManyToOne
    @JoinColumn(name = "idAlerta")
    private Alerta alerta;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idComunicado")
    private Comunicado comunicado;

    @ManyToOne
    @JoinColumn(name = "idResposta")
    private Comentario resposta; // autorreferência

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getStatusRestricao() {
        return statusRestricao;
    }
    public void setStatusRestricao(Boolean statusRestricao) {
        this.statusRestricao = statusRestricao;
    }

    public LocalDateTime getHorarioEnvio() {
        return horarioEnvio;
    }
    public void setHorarioEnvio(LocalDateTime horarioEnvio) {
        this.horarioEnvio = horarioEnvio;
    }

    public Alerta getAlerta() {
        return alerta;
    }
    public void setAlerta(Alerta alerta) {
        this.alerta = alerta;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Comunicado getComunicado() {
        return comunicado;
    }
    public void setComunicado(Comunicado comunicado) {
        this.comunicado = comunicado;
    }

    public Comentario getResposta() {
        return resposta;
    }
    public void setResposta(Comentario resposta) {
        this.resposta = resposta;
    }
}
