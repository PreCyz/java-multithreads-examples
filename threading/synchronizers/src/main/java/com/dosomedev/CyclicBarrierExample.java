package com.dosomedev;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample implements Runnable {

    @Override
    public void run() {
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            IO.println("All workers have reached the checkpoint!");
        });

        for (int i = 1; i <= 3; i++) {
            final int sleepTimeMillis = 1000 * i;

            new Thread(() -> {
                try {
                    // Simulate some work.
                    IO.println("Worker " + Thread.currentThread().threadId() + " start work.");
                    Thread.sleep(sleepTimeMillis);
                    IO.println("Worker " + Thread.currentThread().threadId() + " reaching the checkpoint.");

                    // Wait for all workers to group here.
                    barrier.await();
                    IO.println("Worker " + Thread.currentThread().threadId() + " continuing work.");
                } catch (InterruptedException | BrokenBarrierException ex) {
                    System.err.println(ex.getMessage());
                }
            }).start();
        }
    }
}
