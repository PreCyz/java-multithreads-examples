package com.dosomedev;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class CombinationAsyncExample implements Runnable {
    @Override
    public void run() {
        IO.println("Example with two Futures with combined results:");

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(createIntegerSupplier(1));
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(createIntegerSupplier(2));

        CompletableFuture<Integer> futureResult = future1.thenCombineAsync(future2, (x, y) -> x + y);

        IO.println("This message is displayed right after starting to combine the Futures.");
        try {
            Integer result = futureResult.get();
            IO.println("Result: " + result);
        } catch (InterruptedException | ExecutionException ex) {
            IO.println("Combined Future Error! Message: " + ex.getMessage());
        }
        IO.println("This message is displayed after having waited for the combining Futures.\n");
    }

    private static Supplier<Integer> createIntegerSupplier(int seconds) {
        return () -> {
            App.sleep(Duration.ofSeconds(seconds));
            return seconds;
        };
    }
}
