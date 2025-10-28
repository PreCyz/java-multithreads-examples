package com.dosomedev;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class FutureListExample implements Runnable {
    /**
     * TODO: 1. Show default behaviour discuss the result
     * TODO: 2. Show default behaviour with sleep discuss the result
     * TODO: 3. Show with virtual executor with shoutdown - compare with 1. What does it mean?
     * TODO: 4. Show with platform executor without shoutdown?
     * TODO: 5. Show with platform executor with shoutdown?
    */

    @Override
    public void run() {
        IO.println("Example with a List of Futures run through another Future:");

//        ExecutorService executor = Executors.newFixedThreadPool(3);
//        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        /*List<CompletableFuture<String>> futures = Arrays.asList(
                CompletableFuture.supplyAsync(createSupplier("Andre", Duration.ofSeconds(1)), executor),
                CompletableFuture.supplyAsync(createSupplier("Pawel", Duration.ofSeconds(3)), executor),
                CompletableFuture.supplyAsync(createSupplier("Claus", Duration.ofSeconds(2)), executor)
        );*/

        List<CompletableFuture<String>> futures = Arrays.asList(
                CompletableFuture.supplyAsync(createSupplier("Andre", Duration.ofSeconds(1))),
                CompletableFuture.supplyAsync(createSupplier("Pawel", Duration.ofSeconds(3))),
                CompletableFuture.supplyAsync(createSupplier("Claus", Duration.ofSeconds(2)))
        );

        CompletableFuture
                .allOf(futures.toArray(CompletableFuture[]::new))
                .thenRun(() -> doAfterAll(futures))
                .whenComplete((_, _) -> IO.println("List compute completed!\n"));

//        App.sleep(Duration.ofSeconds(4));
//        executor.shutdown();
    }

    private static Supplier<String> createSupplier(String value, Duration duration) {
        return () -> {
            App.sleep(duration);
            return String.format("%s done in %d sec.", value, duration.toSeconds());
        };
    }

    private static void doAfterAll(List<CompletableFuture<String>> futures) {
        for (CompletableFuture<String> future : futures) {
            try {
                IO.println("Combined Future: " + future.get());
            } catch (ExecutionException | InterruptedException ex) {
                System.err.println("Combined Future Error! Message: " + ex.getMessage());
            }
        }
        App.sleep(Duration.ofMillis(500));
    }
}
