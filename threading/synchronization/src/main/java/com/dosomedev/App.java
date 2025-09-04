package com.dosomedev;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static final PrintStream OUT = System.out;

    public static void main(String[] args) throws InterruptedException {
        synchronizedCounterExample();
        deadlockExample();
    }

    private static void synchronizedCounterExample() throws InterruptedException {
        final long incrementNumber = 1000000;
        final long threadNumber = 100;

        final Counter counter = new Counter();

        Runnable r = () -> {
            for (int i = 1; i <= incrementNumber; i++) {
                counter.incrementNumber();
            }
        };

        // Start all threads.
        List<Thread> threads = new ArrayList<>();
        for (int i = 1; i <= threadNumber; i++) {
            Thread t = new Thread(r);
            threads.add(t);
            t.start();
        }

        // Wait for all threads to terminate.
        for (Thread thread : threads) {
            thread.join();
        }

        // Print results.
        OUT.printf("Counter should be: %s%n",
                String.format("%,d", incrementNumber * threadNumber));
        OUT.printf("Counter is:        %s%n",
                String.format("%,d", counter.getNumber()));
    }

    private static void deadlockExample() {
        final DeadLockCounter counter = new DeadLockCounter();

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

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
    }
}
