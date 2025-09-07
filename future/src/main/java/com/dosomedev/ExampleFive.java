package com.dosomedev;

import java.io.PrintStream;
import java.util.concurrent.*;

public class ExampleFive implements Runnable {

    public static final PrintStream OUT = System.out;

    @Override
    public void run() {
        OUT.println("Example Five (simple task, non-blocking completion):");
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

        while (!future.isDone()) {
            OUT.println("Waiting for task to complete...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                OUT.println("Thread sleep interrupted! Message: " + ex.getMessage());
            }
        }

        try {
            Integer result = future.get();
            OUT.println("Result: " + result);
        } catch (InterruptedException | ExecutionException ex) {
            System.err.println("Error occurred! Message: " + ex.getMessage());
        }

        executor.shutdown();
        executor.close();
        OUT.println();
    }
}
