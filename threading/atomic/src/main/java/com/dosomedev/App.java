package com.dosomedev;

public class App {
    static void main(String[] args) {
//        new VolatileExample().run();
//        new AtomicExample().run();
//        Thread.ofPlatform().start(new VolatileFlagExample());
        new FalseSharing().run();
    }
}
