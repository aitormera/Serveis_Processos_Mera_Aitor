package com.project;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class TascaInteressos implements Runnable {

    private ConcurrentHashMap<String, Double> compte;
    private CountDownLatch dadesRebudes;
    private CountDownLatch dadesModificades;

    public TascaInteressos(ConcurrentHashMap<String, Double> compte,
                           CountDownLatch dadesRebudes,
                           CountDownLatch dadesModificades) {
        this.compte = compte;
        this.dadesRebudes = dadesRebudes;
        this.dadesModificades = dadesModificades;
    }

    public void run() {
        try {
            dadesRebudes.await();
            double saldo = compte.get("saldo");
            double nouSaldo = saldo + saldo * 0.05 - 2.0;
            compte.put("saldo", nouSaldo);
            System.out.println("Interessos: aplicat 5% d'interes i 2.0 de comissio");
            dadesModificades.countDown();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
