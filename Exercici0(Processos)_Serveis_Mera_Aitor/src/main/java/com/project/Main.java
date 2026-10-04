package com.project;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ConcurrentHashMap<String, Double> compte = new ConcurrentHashMap<String, Double>();
        CountDownLatch dadesRebudes = new CountDownLatch(1);
        CountDownLatch dadesModificades = new CountDownLatch(1);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.execute(new TascaRecepcio(compte, dadesRebudes));
        executor.execute(new TascaInteressos(compte, dadesRebudes, dadesModificades));
        Future<Double> resultat = executor.submit(new TascaSaldo(compte, dadesModificades));

        System.out.println("Saldo final del client: " + resultat.get());

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
    }
}
