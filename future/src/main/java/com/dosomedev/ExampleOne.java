package com.dosomedev;

import java.util.concurrent.*;

public class ExampleOne implements Runnable {
    @Override
    public void run() {
        IO.println("Example One (simple task, wait for completion):");
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<Integer> task = () -> {
            try {
                Thread.sleep(2000);
                return 42;
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        };

        Future<Integer> future = executor.submit(task);

        try {
            Integer result = future.get();
            IO.println("Result: " + result);
        } catch (InterruptedException | ExecutionException ex) {
            System.err.println("Error occurred! Message: " + ex.getMessage());
        }

        executor.shutdown();
        executor.close();
        IO.println();
    }
}
