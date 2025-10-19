package com.dosomedev;

import java.util.Timer;
import java.util.TimerTask;

public class TimerIntervalExample implements Runnable {
    @Override
    public void run() {
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            int counter = 1;

            @Override
            public void run() {
                System.out.printf("Interval task at: %s%n", App.currentTime());

                if (counter >= 5) {
                    timer.cancel();
                } else {
                    counter++;
                }
            }
        };

        System.out.printf("Timing interval at: %s%n", App.currentTime());
        timer.schedule(task, 0, 2000);
    }
}
