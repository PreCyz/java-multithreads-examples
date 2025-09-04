package com.dosomedev;

import java.io.PrintStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreExample implements Runnable {

    public static final PrintStream OUT = System.out;

    @Override
    public void run() {
        // Create a semaphore with X permits.
        Semaphore semaphore = new Semaphore(5);

        // Create a thread pool.
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // Submit tasks to the pool.
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                try {
                    // Acquire a permit.
                    OUT.printf("%s --- request.%n", Thread.currentThread().getName());
                    semaphore.acquire();
                    OUT.printf("%s <-- permit.%n", Thread.currentThread().getName());

                    // Simulate work.
                    Thread.sleep(1000);

                    // Release the permit.
                    semaphore.release();
                    OUT.printf("%s --> release.%n", Thread.currentThread().getName());
                } catch (InterruptedException ex) {
                    System.err.println(ex.getMessage());
                }
            });
        }

        // Shutdown the executor.
        executor.shutdown();
        executor.close();
    }
}
