package com.dosomedev;

import java.io.PrintStream;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample implements Runnable {

    public static final PrintStream OUT = System.out;

    @Override
    public void run() {
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            OUT.println("All workers have reached the checkpoint!");
        });

        for (int i = 1; i <= 3; i++) {
            final int sleepTimeMillis = 1000 * i;

            new Thread(() -> {
                try {
                    // Simulate some work.
                    OUT.println("Worker " + Thread.currentThread().getId() + " start work.");
                    Thread.sleep(sleepTimeMillis);
                    OUT.println("Worker " + Thread.currentThread().getId() + " reaching the checkpoint.");

                    // Wait for all workers to group here.
                    barrier.await();
                    OUT.println("Worker " + Thread.currentThread().getId() + " continuing work.");
                } catch (InterruptedException | BrokenBarrierException ex) {
                    System.err.println(ex.getMessage());
                }
            }).start();
        }
    }
}
