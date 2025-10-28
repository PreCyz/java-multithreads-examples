package com.dosomedev;

import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public class App {
    static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CompletionService<CalculateEuler> completion = new ExecutorCompletionService<>(executor);

        List<CalculateEuler> tasks = Stream.of(
                new CalculateEuler(3700),
                new CalculateEuler(1700),
                new CalculateEuler(2700),
                new CalculateEuler(1000)
        )
           .peek(completion::submit)
           .toList();

        executor.shutdown();

        try {
            IO.println();
            for (int i = 0; i < tasks.size(); i++) {

                Future<CalculateEuler> result = completion.take();

                CalculateEuler calculateEuler = result.get();

                System.out.printf("Iterations: %d, euler: %s%n%n", calculateEuler.getIterations(), calculateEuler.getResult());
            }
        } catch (InterruptedException | ExecutionException ex) {
            System.err.println("Error getting Future result!");
        }
    }
}
