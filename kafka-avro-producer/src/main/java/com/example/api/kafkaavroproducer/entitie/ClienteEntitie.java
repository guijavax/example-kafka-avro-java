package com.example.api.kafkaavroproducer.entitie;


public class ClienteEntitie {

    private String nome;
    private Integer cpf;
    private String dataNasc;

    public String getNome() {
        return nome;
    }

    public Integer getCpf() {
        return cpf;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public ClienteEntitie(String nome, Integer cpf, String dataNasc) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
    }
}
