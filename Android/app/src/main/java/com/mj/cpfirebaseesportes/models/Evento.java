package com.mj.cpfirebaseesportes.models;

import java.util.Calendar;

/**
 * Created by munirsalim on 12/11/16.
 */

public class Evento {

    public String esporte;
    public String descricao;
    public String local;
    public Calendar dataHora;
    public Integer nPessoas;
    public Double valor;

    public Evento(String esporte, String descricao, String local, Calendar dataHora, Integer nPessoas, Double valor) {
        this.esporte = esporte;
        this.descricao = descricao;
        this.local = local;
        this.dataHora = dataHora;
        this.nPessoas = nPessoas;
        this.valor = valor;
    }

}
