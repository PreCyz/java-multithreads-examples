package com.dosomedev;

import java.time.Duration;
import java.time.LocalDateTime;

public class VolatileFlagExample implements Runnable {

    //TODO 1: volatile running
    private boolean running = true;
    private int counter = 0;

    @Override
    public void run() {
        var now = LocalDateTime.now();

        Thread workerThread = Thread.ofPlatform().name("worker").start(() -> runnable(now));

        try {
            Thread.sleep(100);

            //TODO 2: reorder instructions see what will happen
            //TODO 3: set counter volatile.
            counter = 1;
            running = false;
            System.out.printf("[%s] changed 'running' to false.%n", Thread.currentThread().getName());

            workerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace(System.err);
        }
    }

    private void runnable(LocalDateTime start) {
        System.out.printf("[%s] was started.", Thread.currentThread().getName());
//            System.out.printf("Worker thread was started with counter equals %d.%n", counter);

        while (running) {

            /*synchronized (this) {
                  //TODO 4: Flushing with synchronized block
                  System.out.printf("%s waiting for synchronized block, counter %d.%n", Thread.currentThread().getName(), counter);
            }*/
//            System.out.printf("%s in the loop with counter equals %d.%n", Thread.currentThread().getName(), counter);
        }

        System.out.printf("%s is done (%dms). Counter is %d: %n",
                Thread.currentThread().getName(),
                Duration.between(start, LocalDateTime.now()).toMillis(),
                counter
        );
    }
}
