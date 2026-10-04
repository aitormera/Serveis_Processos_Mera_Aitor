package com.project;

import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {

        CompletableFuture<Integer> validar = CompletableFuture.supplyAsync(() -> {
            System.out.println("Tasca 1: validant les dades de la sol·licitud...");
            return 10;
        });

        CompletableFuture<Integer> calcular = validar.thenApply(valor -> {
            System.out.println("Tasca 2: processant el valor " + valor + "...");
            return valor * 2 + 5;
        });

        CompletableFuture<Void> mostrar = calcular.thenAccept(resultat -> {
            System.out.println("Tasca 3: resposta a l'usuari -> resultat final: " + resultat);
        });

        mostrar.join();
    }
}
