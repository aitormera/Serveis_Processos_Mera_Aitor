package com.project;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class TascaRecepcio implements Runnable {

    private ConcurrentHashMap<String, Double> compte;
    private CountDownLatch dadesRebudes;

    public TascaRecepcio(ConcurrentHashMap<String, Double> compte, CountDownLatch dadesRebudes) {
        this.compte = compte;
        this.dadesRebudes = dadesRebudes;
    }

    public void run() {
        compte.put("saldo", 1000.0);
        System.out.println("Recepcio: saldo inicial = 1000.0");
        dadesRebudes.countDown();
    }
}
