package com.dosomedev;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class FutureListExample implements Runnable {

    @Override
    public void run() {
        IO.println("Example with a List of Futures run through another Future:");

        List<CompletableFuture<String>> futures = Arrays.asList(
                CompletableFuture.supplyAsync(createSupplier("Andre", Duration.ofSeconds(1))),
                CompletableFuture.supplyAsync(createSupplier("Pawel", Duration.ofSeconds(3))),
                CompletableFuture.supplyAsync(createSupplier("Clause", Duration.ofSeconds(2)))
        );

        CompletableFuture
                .allOf(futures.toArray(CompletableFuture[]::new))
                .thenRun(() -> doAfterAll(futures))
                .whenComplete((_, _) -> IO.println("List compute completed!"));

        App.sleep(Duration.ofSeconds(4));
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
