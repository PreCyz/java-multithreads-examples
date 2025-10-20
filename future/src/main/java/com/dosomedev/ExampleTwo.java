package com.dosomedev;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ExampleTwo implements Runnable {
    @Override
    public void run() {
        IO.println("Example Two (task setting atomic value, wait for completion):");
        ExecutorService executor = Executors.newSingleThreadExecutor();
        AtomicInteger resultHolder = new AtomicInteger();

        Runnable task = () -> {
            try {
                Thread.sleep(2000);
                resultHolder.set(42);
            } catch (InterruptedException ex) {
                System.err.println("Settting result holder interrupted! Message: "
                                   + ex.getMessage());
            }
        };

        Future<?> future = executor.submit(task);

        try {
            future.get();
            IO.println("Result: " + resultHolder.get());
        } catch (InterruptedException | ExecutionException ex) {
            System.err.println("Error occurred! Message: " + ex.getMessage());
        }

        executor.shutdown();
        executor.close();
        IO.println();
    }
}
