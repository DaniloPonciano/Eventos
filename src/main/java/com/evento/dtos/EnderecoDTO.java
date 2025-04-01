package com.evento.dtos;

public class EnderecoDTO {
    private Long id;
    private String numero;
    private String cep;
    private String rua;
    private String bairro;
    private String complemento;
    private CidadeDTO cidade;

    public EnderecoDTO() {}

//    public EnderecoDTO(Long id, String numero, String cep, String rua,
//                       String bairro, String complemento, CidadeDTO cidade) {
//        this.id = id;
//        this.numero = numero;
//        this.cep = cep;
//        this.rua = rua;
//        this.bairro = bairro;
//        this.complemento = complemento;
//        this.cidade = cidade;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public CidadeDTO getCidade() {
        return cidade;
    }

    public void setCidade(CidadeDTO cidade) {
        this.cidade = cidade;
    }
}
