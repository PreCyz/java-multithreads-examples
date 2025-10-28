package com.dosomedev;

import java.util.concurrent.CompletableFuture;

public class CombinationExample implements Runnable {
    @Override
    public void run() {
        IO.println("Example with two CompletableFutures that are combined:");

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Elektroniczny");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Mordulec!");

        future1.thenCombine(future2, (s1, s2) -> s1 + " " + s2)
               .thenAccept(System.out::println);

        IO.println();
    }
}
