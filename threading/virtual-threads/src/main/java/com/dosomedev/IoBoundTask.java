package com.dosomedev;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
public class IoBoundTask implements Runnable {
    private final int taskId;

    @Override
    public void run() {
        try {
            // I/O operation e.g. network call, database query, waiting between 50ms and 200ms.
            Thread.sleep(ThreadLocalRandom.current().nextInt(50, 200));
        } catch (InterruptedException ex) {
            // Re-interrupt the current thread to propagate the interruption signal.
            Thread.currentThread().interrupt();
            System.err.println("Task " + this.taskId + " interrupted.");
        }
    }
}
