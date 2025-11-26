package com.projeto.pix;

import java.util.ArrayList;
import java.util.List;

public class BaseDeChavesSuspeitas {

    private static List<String> chavesDenunciadas = new ArrayList<>();

    static {
        chavesDenunciadas.add("golpe@pix.com");
        chavesDenunciadas.add("12345678900");
        chavesDenunciadas.add("fake@banco.com");
    }

    public static boolean isChaveSuspeita(String chave) {
        return chavesDenunciadas.contains(chave);
    }
}




