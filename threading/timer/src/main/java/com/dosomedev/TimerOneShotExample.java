package com.dosomedev;

import java.util.Timer;
import java.util.TimerTask;

public class TimerOneShotExample implements Runnable {
    @Override
    public void run() {
        // Timer reference for use within TimerTask.
        Timer timer = new Timer();

        // Create thread.
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Print info.
                System.out.printf("One-shot task at: %s%n", App.currentTime());

                // Cancel timer.
                timer.cancel();
            }
        };

        // Schedule one-shot timer execution.
        System.out.printf("Timing one-shot at: %s%n", App.currentTime());
        timer.schedule(task, 1000);
    }
}
