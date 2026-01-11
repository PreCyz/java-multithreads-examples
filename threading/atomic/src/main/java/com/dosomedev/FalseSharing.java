package com.dosomedev;

import java.time.Duration;
import java.time.LocalDateTime;

public class FalseSharing implements Runnable {
    public static class Counter {
        @jdk.internal.vm.annotation.Contended
        public volatile int counter1;
        public volatile int counter2;
    }

    @Override
    public void run() {
        Counter counter1 = new Counter();
        Counter counter2 = counter1;
//        Counter counter2 = new Counter();

        long iterations = 1_000_000_000;

        Thread thread1 = Thread.ofPlatform().name("t1").start(() -> {
            var startTime = LocalDateTime.now();
            for (long i = 0; i < iterations; i++) {
                counter1.counter1++;
            }
            System.out.printf("total time: %d%n", Duration.between(startTime, LocalDateTime.now()).toMillis());
        });
        Thread thread2 = Thread.ofPlatform().name("t1").start(() -> {
            var startTime = LocalDateTime.now();
            for (long i = 0; i < iterations; i++) {
                counter2.counter2++;
            }
            System.out.printf("total time: %d%n", Duration.between(startTime, LocalDateTime.now()).toMillis());
        });

    }
}
