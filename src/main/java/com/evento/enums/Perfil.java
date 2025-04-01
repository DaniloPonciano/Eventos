package com.evento.enums;

public enum Perfil {
    ADMINISTRADOR("Adiministrador"),
    FUNCIONARIO("Funcionário"),
    GERENTE("Gerente");

    private String descricao;

    Perfil(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}
