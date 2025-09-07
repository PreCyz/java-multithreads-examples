package com.dosomedev;

import java.util.concurrent.*;

public class ExampleThree implements Runnable {
    @Override
    public void run() {
        System.out.println("Example Three (simple task, completion cancellation):");
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

        // Simulate a cancellation request.
        new Thread(() -> {
            try {
                Thread.sleep(800);
                System.out.println("Canceling task...");
                future.cancel(true);
            } catch (InterruptedException ex) {
                System.err.println("Cancel thread interrupted! Message: " + ex.getMessage());
            }
        }).start();

        try {
            Integer result = future.get();
            System.out.println("Result: " + result);
        } catch (CancellationException e) {
            System.out.println("Task was canceled.");
        } catch (InterruptedException | ExecutionException ex) {
            System.err.println("Error occurred! Message: " + ex.getMessage());
        }

        executor.shutdown();
        executor.close();
        System.out.println();
    }
}
