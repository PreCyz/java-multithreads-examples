package com.dosomedev;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class App {

    private static final int INCREMENT_NUMBER = 1_000_000;

    //TODO: Show what will happen if threads number is increased.
    private static final int THREAD_NUMBER = 100;

    static void main(String[] args) throws InterruptedException {
        //TODO: no synchronization
//        counterExample(Counter::increment);

        //TODO: properly synchronized
//        counterExample(Counter::incrementSyncThis);
//        counterExample(Counter::incrementSyncMethod);
//        counterExample(Counter::incrementSyncOnMonitorObject);

        //TODO: No fairness example
//        threadSchedulingExample();

        //TODO: non-synchronized method is called from synchronized method.
        nestedMethodExample();

//        deadlockExample();
    }

    private static void counterExample(Consumer<Counter> consumer) throws InterruptedException {
        final var now = LocalDateTime.now();
        final var counter = new Counter();

        Runnable runnable = () -> {
            for (int i = 1; i <= INCREMENT_NUMBER; i++) {
                consumer.accept(counter);
            }
        };

        List<Thread> threads = new ArrayList<>(THREAD_NUMBER);
        for (int i = 1; i <= THREAD_NUMBER; i++) {
            threads.add(Thread.ofPlatform().start(runnable));
        }

        for (Thread thread : threads) {
            thread.join();
        }

        var duration = Duration.between(now, LocalDateTime.now());
        System.out.printf("Counter should be: %s%n", df(' ').format(INCREMENT_NUMBER * THREAD_NUMBER));
        System.out.printf("Counter is:        %s%n", df(' ').format(counter.getNumber()));
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

    private static DecimalFormat df(char separator) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(separator);
        DecimalFormat df = new DecimalFormat("#,###");
        df.setDecimalFormatSymbols(symbols);
        return df;
    }

    private static void nestedMethodExample() throws InterruptedException {
        final var now = LocalDateTime.now();
        final var counter = new Counter();

        Runnable r1 = () -> {
            for (int i = 1; i <= INCREMENT_NUMBER; i++) {
                counter.increment();
            }
        };

        Runnable r2 = () -> {
            for (int i = 1; i <= INCREMENT_NUMBER; i++) {
                counter.incWait();
            }
        };

        List<Thread> threads = new ArrayList<>(THREAD_NUMBER);
        for (int i = 1; i <= THREAD_NUMBER; i++) {
            if (i % 2 == 0) {
                threads.add(Thread.ofPlatform().start(r1));
            } else {
                threads.add(Thread.ofPlatform().start(r2));
            }
        }

        for (Thread thread : threads) {
            thread.join();
        }

        var duration = Duration.between(now, LocalDateTime.now());
        System.out.printf("Counter should be: %s%n", df(' ').format(INCREMENT_NUMBER * THREAD_NUMBER));
        System.out.printf("Counter is:        %s%n", df(' ').format(counter.getNumber()));
        System.out.printf("Duration: %d.%d sec%n", duration.toSecondsPart(), duration.toMillisPart());
        IO.println("======================");
    }
}
