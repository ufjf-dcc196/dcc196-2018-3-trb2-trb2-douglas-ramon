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
    private List<Participante> participantes;

    public Evento() {
    }

    public Evento(String titulo, String data, Calendar hora, String facilitador, String descricao, List<Participante> participantes) {
        this.titulo = titulo;
        this.data = data;
        this.hora = hora;
        this.facilitador = facilitador;
        this.descricao = descricao;
        this.participantes = participantes;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
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