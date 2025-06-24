package com.floodguard.floodguard_server.dto;

import java.time.LocalDateTime;

public class ComentarioResponseDTO {
    private Long id;
    private String conteudo;
    private LocalDateTime horarioEnvio;
    private Boolean statusRestricao;
    private Long idUsuario; // ID do usuário que fez o comentário
    private String nomeUsuario; // Nome do usuário que fez o comentário

    public ComentarioResponseDTO() {}

    public ComentarioResponseDTO(Long id, String conteudo, LocalDateTime horarioEnvio, Boolean statusRestricao, Long idUsuario, String nomeUsuario) {
        this.id = id;
        this.conteudo = conteudo;
        this.horarioEnvio = horarioEnvio;
        this.statusRestricao = statusRestricao;
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getHorarioEnvio() {
        return horarioEnvio;
    }

    public void setHorarioEnvio(LocalDateTime horarioEnvio) {
        this.horarioEnvio = horarioEnvio;
    }

    public Boolean getStatusRestricao() {
        return statusRestricao;
    }

    public void setStatusRestricao(Boolean statusRestricao) {
        this.statusRestricao = statusRestricao;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
}