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
    private double lat;
    private double lng;
    private Calendar dataHora;
    private Integer nPessoas;
    private Double valor;

    public Evento() {}

    public Evento(String titulo, String esporte, String descricao, String local, double lat, double lng, Calendar dataHora, Integer nPessoas, Double valor) {
        this.titulo = titulo;
        this.esporte = esporte;
        this.descricao = descricao;
        this.local = local;
        this.dataHora = dataHora;
        this.nPessoas = nPessoas;
        this.valor = valor;
        this.lat = lat;
        this.lng = lng;
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

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "titulo='" + titulo + '\'' +
                ", esporte='" + esporte + '\'' +
                ", descricao='" + descricao + '\'' +
                ", local='" + local + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", dataHora=" + dataHora +
                ", nPessoas=" + nPessoas +
                ", valor=" + valor +
                '}';
    }
}
