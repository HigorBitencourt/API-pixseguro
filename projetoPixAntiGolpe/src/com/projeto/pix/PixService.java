package com.projeto.pix;

public class PixService {

    public static String analisarTransacao(TransacaoPix transacao) {

        if (BaseDeChavesSuspeitas.isChaveSuspeita(transacao.getChaveDestino())) {
            return "❌ ALERTA: Essa chave Pix já foi denunciada por golpe!";
        }

        if (transacao.getValor() >= 1000) {
            return "⚠ ATENÇÃO: Valor alto detectado. Confirme se não é um golpe!";
        }

        return "✅ Transação considerada segura.";
    }
}
