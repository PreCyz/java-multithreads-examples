package com.dosomedev;

import java.math.BigDecimal;
import java.util.concurrent.*;

public class App {
    static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CompletionService<BigDecimal> completion = new ExecutorCompletionService<>(executor);

        Future<BigDecimal> futureB = completion.submit(new CalculateEuler(170));
        Future<BigDecimal> futureC = completion.submit(new CalculateEuler(1700));
        Future<BigDecimal> futureA = completion.submit(new CalculateEuler(17));

        executor.shutdown();

        try {
            for (int i = 1; i <= 3; i++) {
                Future<BigDecimal> result = completion.take();
                IO.println(result.get());
                IO.println();
            }
        } catch (InterruptedException | ExecutionException ex) {
            System.err.println("Error getting Future result!");
        }
    }
}
