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
@Table(name = "cidade")
public class Cidade {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_uf", nullable = false)
    private Uf uf;

    // Construtores
    public Cidade() {} // Construtor vazio é necessário para a JPA

    public Cidade(String nome, Uf uf) {
        this.nome = nome;
        this.uf = uf;
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Uf getUf() { return uf; }
    public void setUf(Uf uf) { this.uf = uf; }
}
