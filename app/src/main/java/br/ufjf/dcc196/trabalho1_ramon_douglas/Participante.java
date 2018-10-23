package br.ufjf.dcc196.trabalho1_ramon_douglas;

import java.io.Serializable;
import java.util.List;

public class Participante implements Serializable {
    private String nome;
    private String email;
    private int cpf;
    private List<Evento> eventos;

    public Participante() {
    }

    public Participante(String nome, String email, int cpf) {
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

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
}
