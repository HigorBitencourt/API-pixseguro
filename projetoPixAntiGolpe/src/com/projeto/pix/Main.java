package com.projeto.pix;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== SISTEMA ANTI GOLPE PIX ===");

        System.out.print("Digite a chave Pix de destino: ");
        String chave = scanner.nextLine();

        System.out.print("Digite o valor do Pix: R$ ");
        double valor = scanner.nextDouble();

        TransacaoPix transacao = new TransacaoPix(chave, valor);

        String resultado = PixService.analisarTransacao(transacao);

        System.out.println("\nResultado da análise:");
        System.out.println(resultado);
    }
}
