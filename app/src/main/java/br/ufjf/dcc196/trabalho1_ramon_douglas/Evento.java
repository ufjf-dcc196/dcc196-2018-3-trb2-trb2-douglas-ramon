package br.ufjf.dcc196.trabalho1_ramon_douglas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Evento {

    private String titulo;
    private String data;
    private Calendar hora;
    private String facilitador;
    private String descricao;

    public Evento() {
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Calendar getHora() {
        return hora;
    }

    public void setHora(Calendar hora) {
        this.hora = hora;
    }

    public String getFacilitador() {
        return facilitador;
    }

    public void setFacilitador(String facilitador) {
        this.facilitador = facilitador;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}