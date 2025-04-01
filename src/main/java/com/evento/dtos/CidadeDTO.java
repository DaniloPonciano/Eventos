package com.evento.dtos;

import java.util.Objects;

public class CidadeDTO {

    private Long id;
    private String estado;
    private String nome;

    public CidadeDTO() {
    }

    public CidadeDTO(Long id, String estado, String nome) {
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
}
