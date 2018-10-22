package br.ufjf.dcc196.trabalho1_ramon_douglas;

import java.util.List;

public class Participante {

    private String nome;
    private String email;
    private String cpf;

    public Participante() {
    }

    public Participante(String nome, String email, String cpf) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}