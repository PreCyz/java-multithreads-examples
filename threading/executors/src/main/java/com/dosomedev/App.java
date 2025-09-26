package com.dosomedev;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class App {
    final static int PRECISION = 100;

    final static int TERMS = 4000;

    final static int POOL = 2;

    final static int THREADS = 10;


    public static void main(String[] args) {
        System.out.printf("Precision: %s%n", PRECISION);
        System.out.printf("Terms:     %s%n", TERMS);
        System.out.printf("Pool:      %s%n", POOL);
        System.out.printf("Threads:   %s%n", THREADS);

        // Define executor pool.
        //ExecutorService executor = Executors.newFixedThreadPool(POOL);
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(POOL);

        // Define process.
        Callable<BigDecimal> callable = new Callable<>() {
            @Override
            public BigDecimal call() throws Exception {
                MathContext mc = new MathContext(PRECISION, RoundingMode.HALF_UP);

                BigDecimal result = BigDecimal.ZERO;
                for (int i = 0; i <= TERMS; i++) {
                    BigDecimal factorial = factorial(new BigDecimal(i));
                    BigDecimal inverse = BigDecimal.ONE.divide(factorial, mc);
                    result = result.add(inverse);
                }

                return result.setScale(PRECISION, RoundingMode.HALF_UP);
            }

            private BigDecimal factorial(BigDecimal n) {
                BigDecimal result = BigDecimal.ONE;
                for (BigDecimal i = BigDecimal.valueOf(2); i.compareTo(n) <= 0; i = i.add(BigDecimal.ONE)) {
                    result = result.multiply(i);
                }
                return result;
            }
        };

        List<Future<BigDecimal>> tasks = new ArrayList<>();
        for (int i = 1; i <= THREADS; i++) {
            Future<BigDecimal> task = executor.submit(callable);
            tasks.add(task);
        }

        executor.shutdown();

        boolean executorTerminated = false;
        while (!executorTerminated) {
            if (executor.isTerminated()) {
                executorTerminated = true;
            }

            boolean shutdown = executor.isShutdown();
            boolean terminated = executor.isTerminated();
            int pending = executor.getQueue().size();
            int active = executor.getActiveCount();
            long completed = executor.getCompletedTaskCount();
            System.out.printf("pending: %s, active: %s, completed: %s, shutdown: %s, terminated: %s%n", pending, active, completed, shutdown, terminated);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("Sleep interrupted!");
            }
        }

        try {
            BigDecimal eulersNumber = tasks.get(0).get();
            System.out.printf("First thread result: %s%n", eulersNumber);
        } catch (InterruptedException | ExecutionException ex) {
            System.err.println("Could not grab result!");
        }
    }
}
