package com.dosomedev;

import java.time.LocalDateTime;

/** Threading example. */
public class App {
    static void main(String[] args) {
        new ThreadLocalExample().run();
        new InheritableThreadLocalExample().run();
        new TimerOneShotExample().run();
        new TimerIntervalExample().run();
    }

    static String currentTime() {
        return LocalDateTime.now().toLocalTime().toString();
    }
}
