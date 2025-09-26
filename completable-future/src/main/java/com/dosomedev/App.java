package com.dosomedev;

/**
 * CompletableFuture example.
 *
 */
public class App {
    public static void main(String[] args) {
        CombinationExample combinationExample = new CombinationExample();
        combinationExample.run();
        IO.println();

        CombinationAsyncExample combinationAsyncExample = new CombinationAsyncExample();
        combinationAsyncExample.run();
        IO.println();

        ThrowErrorExample throwErrorExample = new ThrowErrorExample();
        throwErrorExample.run();
        IO.println();

        FutureListExample futureListExample = new FutureListExample();
        futureListExample.run();
        IO.println();
    }
}
