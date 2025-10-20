package com.dosomedev;

import java.math.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class App {
    final static int POOL_SIZE = 2;
//    final static int POOL_SIZE = Runtime.getRuntime().availableProcessors();
    final static int TASKS = 10;

    static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.printf("Pool:      %s%n", POOL_SIZE);
        System.out.printf("Tasks:     %s%n", TASKS);

        try (ExecutorService executor = Executors.newFixedThreadPool(POOL_SIZE)) {

            Callable<BigDecimal> callable = calculateEulerNumber();

            List<Future<BigDecimal>> tasks = new ArrayList<>();
            for (int i = 1; i <= TASKS; i++) {
                Future<BigDecimal> task = executor.submit(callable);
                tasks.add(task);
            }

            executor.shutdown();

            processTasksWithThreadPoolExecutor((ThreadPoolExecutor) executor, tasks);

        } finally {
            System.out.printf("%nDone in %d sec.%n", Duration.between(now, LocalDateTime.now()).toSeconds());
        }
    }

    private static void processTasksWithThreadPoolExecutor(ThreadPoolExecutor executor, List<Future<BigDecimal>> tasks) {
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
            System.out.printf("pending: %s, active: %s, completed: %s, shutdown: %s, terminated: %s%n",
                    pending, active, completed, shutdown, terminated);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("Sleep interrupted!");
            }
        }

        try {
            System.out.printf("First thread result: %s%n", tasks.getFirst().get());
        } catch (InterruptedException | ExecutionException ex) {
            System.err.println("Could not grab result!");
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
