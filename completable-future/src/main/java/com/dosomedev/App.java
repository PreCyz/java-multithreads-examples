package com.dosomedev;

import java.time.Duration;

/** CompletableFuture example. */
public class App {

    static void main(String[] args) {
        new CombinationExample().run();

        new CombinationAsyncExample().run();

        new ThrowErrorExample().run();

        new FutureListExample().run();
    }

    static void sleep(Duration duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            IO.println("Interrupted!");
        }
    }
}
