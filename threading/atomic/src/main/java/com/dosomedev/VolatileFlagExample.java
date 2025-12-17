package com.dosomedev;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.function.Function;

public class VolatileFlagExample implements Runnable {

    //TODO 1: run application then change running to volatile
    private boolean running = true;
    private int counter = 0;

    /** Case 1 when
     * running = false;
     * counter = 1;
     * then running is flushed to the main memory but counter is not, however there is race going on and in most cases
     * when the worker thread completed loop and just before printed out the result, somehow the counter is also flashed
     * to the main memory. If you run the application more than several times you will finally see printout showing
     * counter 0!
     * Case 2 when
     * counter = 1;
     * running = false;
     * when false is written to the running then all available variables to that thread at the time of writing to the volatile
     * 'running' are flushed to the main memory so the worker printout will show always counter 1.
     */
    @Override
    public void run() {
        var now = LocalDateTime.now();

        Function<LocalDateTime, Long> calculateDuration = (LocalDateTime end) -> Duration.between(now, end).toMillis();

        var workerThread = Thread.ofPlatform().name("worker").start(() -> runnable(calculateDuration));

        try {
            Thread.sleep(15);

            //TODO 2: reorder instructions see what will happen
            counter = 1;
            running = false;
            System.out.println("[main] changed 'running' to false and counter to 1.");

            workerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace(System.err);
        }
    }

    private void runnable(Function<LocalDateTime, Long> duration) {
        System.out.println("[worker] was started.");
//            System.out.printf("[worker] thread was started with counter equals %d.%n", counter);

        while (running) {

            /*synchronized (this) {
                  //TODO 3: Flushing with synchronized block
                  System.out.printf("[worker] waiting for synchronized block, counter %d.%n", counter);
            }*/
//            System.out.printf("[worker] in the loop with counter equals %d.%n", counter);
        }
        var tmp = counter;

        System.out.printf("[worker] is done (%dms). Counter is %d: %n", duration.apply(LocalDateTime.now()), tmp);
    }
}
