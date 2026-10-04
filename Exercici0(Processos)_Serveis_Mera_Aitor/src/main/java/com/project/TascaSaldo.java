package com.project;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class TascaSaldo implements Callable<Double> {

    private ConcurrentHashMap<String, Double> compte;
    private CountDownLatch dadesModificades;

    public TascaSaldo(ConcurrentHashMap<String, Double> compte, CountDownLatch dadesModificades) {
        this.compte = compte;
        this.dadesModificades = dadesModificades;
    }

    public Double call() throws Exception {
        dadesModificades.await();
        return compte.get("saldo");
    }
}
