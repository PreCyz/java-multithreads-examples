package com.dosomedev;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class App {

    private static final int INCREMENT_NUMBER = 1_000_000;

    private static final int THREAD_NUMBER = 2;

    static void main(String[] args) throws InterruptedException {
        counterExample((Counter c) -> {
            c.increment();
            return Optional.empty();
        });

        counterExample((Counter c) -> {
            c.incrementSyncThis();
            return Optional.empty();
        });
        counterExample((Counter c) -> {
            c.incrementSyncMethod();
            return Optional.empty();
        });
        counterExample((Counter c) -> {
            c.incrementSyncOnMonitorObject();
            return Optional.empty();
        });

        threadSchedulingExample();

        deadlockExample();
    }

    private static void counterExample(Function<Counter, Optional<?>> function) throws InterruptedException {
        final var now = LocalDateTime.now();
        final var counter = new Counter();

        Runnable r = () -> {
            for (int i = 1; i <= INCREMENT_NUMBER; i++) {
                function.apply(counter);
            }
        };

        List<Thread> threads = new ArrayList<>(THREAD_NUMBER);
        for (int i = 1; i <= THREAD_NUMBER; i++) {
            threads.add(Thread.ofPlatform().start(r));
        }

        for (Thread thread : threads) {
            thread.join();
        }

        var duration = Duration.between(now, LocalDateTime.now());
        System.out.printf("Counter should be: %s%n", String.format("%,d", INCREMENT_NUMBER * THREAD_NUMBER).replace(",", "_"));
        System.out.printf("Counter is:        %s%n", String.format("%,d", counter.getNumber()).replace(",", "_"));
        System.out.printf("Duration: %d.%d sec%n", duration.toSecondsPart(), duration.toMillisPart());
        IO.println("======================");
    }

    /** This method visualizes that there is no guarantee the exact order of the threads! */
    private static void threadSchedulingExample() {
        final int incrementNumber = 1_000;

        final var exchanger = new SynchronizedExchanger();

        Thread.ofPlatform().start(() -> {
            for (int i = 1; i <= incrementNumber; i++) {
                exchanger.setObject(String.valueOf(i));
            }
        });
        Thread.ofPlatform().start(() -> {
            for (int i = 1; i <= incrementNumber; i++) {
                System.out.println(exchanger.getObject());
            }
        });
    }

    /** Deadlock occurs after short time. */
    private static void deadlockExample() {
        final var counter = new DeadLockCounter();

        Runnable r1 = () -> {
            while (true) {
                counter.incrementA();

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
            }
        };

        Runnable r2 = () -> {
            while (true) {
                counter.incrementB();

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
            }
        };

        Thread.ofPlatform().start(r1);
        Thread.ofPlatform().start(r2);
    }
}
