package com.dosomedev;

import java.util.Timer;
import java.util.TimerTask;

public class TimerIntervalExample implements Runnable {
    @Override
    public void run() {
        // Timer reference for use within TimerTask.
        Timer timer = new Timer();

        // Create thread.
        TimerTask task = new TimerTask() {
            int counter = 1;

            @Override
            public void run() {
                // Print info.
                System.out.printf("Interval task at: %s%n", App.currentTime());

                if (counter >= 5) {
                    // Cancel interval.
                    timer.cancel();
                } else {
                    counter++;
                }
            }
        };

        // Schedule interval timer execution.
        System.out.printf("Timing interval at: %s%n", App.currentTime());
        timer.schedule(task, 0, 2000);
    }
}
