package com.dosomedev;

import java.time.Duration;

/** CompletableFuture example. */
public class App {

    static void main(String[] args) {
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

    static void sleep(Duration duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            IO.println("Interrupted!");
        }
    }
}
