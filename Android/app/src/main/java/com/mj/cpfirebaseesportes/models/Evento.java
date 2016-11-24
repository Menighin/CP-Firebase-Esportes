package com.mj.cpfirebaseesportes.models;

import java.util.Calendar;

/**
 * Created by munirsalim on 12/11/16.
 */

public class Evento {

    private String titulo;
    private String esporte;
    private String descricao;
    private String local;
    private Calendar dataHora;
    private Integer nPessoas;
    private Double valor;

    public Evento(String titulo, String esporte, String descricao, String local, Calendar dataHora, Integer nPessoas, Double valor) {
        this.titulo = titulo;
        this.esporte = esporte;
        this.descricao = descricao;
        this.local = local;
        this.dataHora = dataHora;
        this.nPessoas = nPessoas;
        this.valor = valor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getEsporte() {
        return esporte;
    }

    public void setEsporte(String esporte) {
        this.esporte = esporte;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Calendar getDataHora() {
        return dataHora;
    }

    public void setDataHora(Calendar dataHora) {
        this.dataHora = dataHora;
    }

    public Integer getnPessoas() {
        return nPessoas;
    }

    public void setnPessoas(Integer nPessoas) {
        this.nPessoas = nPessoas;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
