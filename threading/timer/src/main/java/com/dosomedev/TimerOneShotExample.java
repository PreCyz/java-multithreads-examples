package com.dosomedev;

import java.util.Timer;
import java.util.TimerTask;

public class TimerOneShotExample implements Runnable {
    @Override
    public void run() {
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.printf("One-shot task at: %s%n", App.currentTime());
                timer.cancel();
            }
        };

        System.out.printf("Timing one-shot at: %s%n", App.currentTime());
        timer.schedule(task, 1000);
    }
}
