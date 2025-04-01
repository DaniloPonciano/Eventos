package com.evento.models;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "cidades")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String estado;
    private String nome;

    public Cidade() {
    }

    public Cidade(Long id, String estado, String nome) {
        this.id = id;
        this.estado = estado;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cidade cidade = (Cidade) o;
        return Objects.equals(id, cidade.id) && Objects.equals(estado, cidade.estado) && Objects.equals(nome, cidade.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, estado, nome);
    }
}
