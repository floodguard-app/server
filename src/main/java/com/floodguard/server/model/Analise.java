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
@Table(name = "analise")
public class Analise {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "resumo", nullable = false)
    private String resumo;

    @Column(name = "recomendacoes", columnDefinition = "TEXT", nullable = true)
    private String recomendacoes;

    @ManyToOne
    @JoinColumn(name = "id_alerta", nullable = false)
    private Alerta alerta;

    // Construtores
    public Analise() {} // Construtor vazio é necessário para a JPA

    public Analise(String resumo, Alerta alerta) {
        this.resumo = resumo;
        this.alerta = alerta;
    }

    public Analise(String resumo, String recomendacoes, Alerta alerta) {
        this.resumo = resumo;
        this.recomendacoes = recomendacoes;
        this.alerta = alerta;
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getResumo() { return resumo; }
    public void setResumo(String resumo) { this.resumo = resumo; }

    public String getRecomendacoes() { return recomendacoes; }
    public void setRecomendacoes(String recomendacoes) { this.recomendacoes = recomendacoes; }

    public Alerta getAlerta() { return alerta; }
    public void setAlerta(Alerta alerta) { this.alerta = alerta; }
}