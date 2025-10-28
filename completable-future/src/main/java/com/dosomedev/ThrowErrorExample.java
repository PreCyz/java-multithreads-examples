package com.dosomedev;

import java.util.concurrent.CompletableFuture;

public class ThrowErrorExample implements Runnable {
    @Override
    public void run() {
        IO.println("Example with one Future that throws an error:");

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Some Error Message");
//            return 7;
        });

//        future.exceptionally(ex -> {
//            IO.println("Exception: " + ex.getMessage());
//            return 0;
//        }).thenAccept((result) -> System.out.printf("Final msg: %d%n", result));

        future.handle((result, exception) -> {
            if (exception != null) {
                IO.println("Exception: " + exception.getMessage());
                return -1;
            } else {
                return result;
            }
        }).thenAccept((result) -> System.out.printf("Final result: %d%n%n", result));

    }
}
