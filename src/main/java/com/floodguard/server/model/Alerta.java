package com.floodguard.server.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "alerta")
public class Alerta {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column(name = "nivel", nullable = false)
    private Integer nivel;

    @Column(name = "mensagem", length = 255, nullable = true)
    private String mensagem;

    @ManyToOne
    @JoinColumn(name = "id_regiao", nullable = false)
    private Regiao regiao;

    // Construtores
    public Alerta() {} // Construtor vazio é necessário para a JPA

    public Alerta(Integer nivel, Regiao regiao) {
        this.nivel = nivel;
        this.regiao = regiao;
    }

    public Alerta(Integer nivel, String mensagem, Regiao regiao) {
        this.nivel = nivel;
        this.mensagem = mensagem;
        this.regiao = regiao;
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getNivel() { return nivel; }
    public void setNivel(Integer nivel) { this.nivel = nivel; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public Regiao getRegiao() { return regiao; }
    public void setRegiao(Regiao regiao) { this.regiao = regiao; }
}
