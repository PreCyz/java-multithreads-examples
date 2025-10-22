package com.dosomedev;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class App {
    final static int POOL_SIZE = 2;
//    final static int POOL_SIZE = Runtime.getRuntime().availableProcessors();
    final static int TASKS = 10;

    static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.printf("Pool:      %s%n", POOL_SIZE);
        System.out.printf("Tasks:     %s%n", TASKS);

        try (ExecutorService executor = Executors.newFixedThreadPool(POOL_SIZE)) {
//        try (ExecutorService executor = Executors.newCachedThreadPool()) {

            Callable<BigDecimal> callable = calculateEulerNumber();

            List<Future<BigDecimal>> tasks = new ArrayList<>();
            for (int i = 1; i <= TASKS; i++) {
                Future<BigDecimal> task = executor.submit(callable);
                tasks.add(task);
            }

            executor.shutdown();
//            executor.awaitTermination(10, TimeUnit.SECONDS);
//            executor.shutdownNow();

            processTasksWithThreadPoolExecutor(executor, tasks);

        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            System.out.printf("%nDone in %d sec.%n", Duration.between(now, LocalDateTime.now()).toSeconds());
        }
    }

    private static void processTasksWithThreadPoolExecutor(ExecutorService executor, List<Future<BigDecimal>> tasks) {
        boolean executorTerminated = false;
        while (!executorTerminated) {
            if (executor.isTerminated()) {
                executorTerminated = true;
            }
            logProgress(executor);
            sleep(1, TimeUnit.SECONDS);
        }

        try {
            Future<BigDecimal> eulerNumber = tasks.getFirst();
            System.out.printf("First thread result: %s%n", eulerNumber.get());
        } catch (InterruptedException | ExecutionException ex) {
            System.err.println("Could not grab result!");
        }
    }

    private static void logProgress(ExecutorService executorService) {
        if (executorService instanceof ThreadPoolExecutor executor) {
            boolean shutdown = executor.isShutdown();
            boolean terminated = executor.isTerminated();
            int pending = executor.getQueue().size();
            int active = executor.getActiveCount();
            long completed = executor.getCompletedTaskCount();
            System.out.printf("pending: %s, active: %s, completed: %s, shutdown: %s, terminated: %s%n",
                    pending, active, completed, shutdown, terminated);
        }
    }

    private static void sleep(long value, TimeUnit timeUnit) {
        try {
            Thread.sleep(timeUnit.toMillis(value));
        } catch (InterruptedException e) {
            System.err.println("Sleep interrupted!");
        }
    }

    private static Callable<BigDecimal> calculateEulerNumber() {
        return new Callable<>() {
            final static int PRECISION = 100;
            final static int TERMS = 4000;

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
    }
}
