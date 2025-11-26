package com.projeto.pix;

public class TransacaoPix {

    private String chaveDestino;
    private double valor;

    public TransacaoPix(String chaveDestino, double valor) {
        this.chaveDestino = chaveDestino;
        this.valor = valor;
    }

    public String getChaveDestino() {
        return chaveDestino;
    }

    public double getValor() {
        return valor;
    }
}




