package com.dosomedev;

import java.util.concurrent.*;

public class ExampleFour implements Runnable {
    @Override
    public void run() {
        IO.println("Example Four (simple task, completion timeout):");
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
            Integer result = future.get(800, TimeUnit.MILLISECONDS);
            IO.println("Result: " + result);
        } catch (TimeoutException e) {
            IO.println("Task timed out.");
        } catch (InterruptedException | ExecutionException ex) {
            System.err.println("Error occurred! Message: " + ex.getMessage());
        }

        executor.shutdown();
        executor.close();
        IO.println();
    }
}
