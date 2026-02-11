package com.dosomedev;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreRecursionExample implements Runnable {

    public void recursion(Semaphore semaphore) throws InterruptedException {
        Thread thread = Thread.currentThread();
        System.out.printf("%nThread: %s enters recursion %n", thread.getName());

        // Acquire a permit.
        System.out.printf("%s --- request.%n", Thread.currentThread().getName());
        semaphore.acquire();
        System.out.printf("%s <-- permit.%n", Thread.currentThread().getName());

        // Simulate work.
        TimeUnit.SECONDS.sleep(1);

        recursion(semaphore);

        // Release the permit.
        semaphore.release();
        System.out.printf("%s --> release.%n", Thread.currentThread().getName());
    }

    @Override
    public void run() {
        Semaphore semaphore = new Semaphore(5);

        ExecutorService executor = Executors.newFixedThreadPool(15);

        // Submit tasks to the pool.
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                try {
                    recursion(semaphore);
                } catch (InterruptedException ex) {
                    System.err.println(ex.getMessage());
                }
            });
        }

//        release(executor, semaphore);

        // Shutdown the executor.
        executor.close();
    }


    /** This shows how the same semaphore can be released from different thread, unlike ReentrantLock. */
    private void release(ExecutorService executor, Semaphore semaphore) {
        for (int i = 0; i < 15; i++) {
            executor.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    Thread thread = Thread.currentThread();
                    System.out.printf("%nThread: %s waited 10 sec and RELEASE semaphore %n", thread.getName());
                    semaphore.release();
                } catch (InterruptedException ex) {
                    System.err.println(ex.getMessage());
                }
            });
        }
    }
}
