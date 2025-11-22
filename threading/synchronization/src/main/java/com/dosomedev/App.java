package com.dosomedev;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class App {

    static void main(String[] args) throws InterruptedException {
        synchronizedCounterExample((Counter c) -> { c.incrementNotSync(); return null;});
        synchronizedCounterExample((Counter c) -> { c.incrementSyncThis(); return null;});
        synchronizedCounterExample((Counter c) -> { c.incrementSyncMethod(); return null;});
        synchronizedCounterExample((Counter c) -> { c.incrementSyncOnMonitorObject(); return null;});

//        threadScheduling();

//        deadlockExample();
    }

    private static void synchronizedCounterExample(Function<Counter, Void> function) throws InterruptedException {
        final var now = LocalDateTime.now();
        final int incrementNumber = 1_000_000;
        final int threadNumber = 100;
        final var counter = new Counter();

        Runnable r = () -> {
            for (int i = 1; i <= incrementNumber; i++) {
                function.apply(counter);
            }
        };

        // Start all threads.
        List<Thread> threads = new ArrayList<>(threadNumber);
        for (int i = 1; i <= threadNumber; i++) {
            threads.add(Thread.ofPlatform().start(r));
        }

        // Wait for all threads to terminate.
        for (Thread thread : threads) {
            thread.join();
        }

        var duration = Duration.between(now, LocalDateTime.now());
        // Print results.
        System.out.printf("Counter should be: %s%n", String.format("%,d", incrementNumber * threadNumber));
        System.out.printf("Counter is:        %s%n", String.format("%,d", counter.getNumber()));
        System.out.printf("Duration: %d.%d sec%n", duration.toSecondsPart(), duration.toMillisPart());
        IO.println("======================");
    }

    /** This method visualizes that there is no guarantee the exact order of the threads!
     **/
    private static void threadScheduling() {
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
