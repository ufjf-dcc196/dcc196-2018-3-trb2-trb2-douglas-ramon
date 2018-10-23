package br.ufjf.dcc196.trabalho1_ramon_douglas;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Evento {
    private String titulo;
    private String facilitador;
    private Date data;
    private Date hora;
    private String descricao;
    private List<Participante> participantes;

    public Evento() {
    }

    public Evento(String titulo, String facilitador, Date data, Date hora, String descricao) {
        this.titulo = titulo;
        this.facilitador = facilitador;
        this.data = data;
        this.hora = hora;
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFacilitador() {
        return facilitador;
    }

    public void setFacilitador(String facilitador) {
        this.facilitador = facilitador;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }
}
