package com.dosomedev;

import java.time.LocalDateTime;

/** Threading example. */
public class App {
    static void main(String[] args) {
        ThreadLocalExample example1 = new ThreadLocalExample();
        example1.run();

        InheritableThreadLocalExample example2 = new InheritableThreadLocalExample();
        example2.run();

        TimerOneShotExample example3 = new TimerOneShotExample();
        example3.run();

        TimerIntervalExample example4 = new TimerIntervalExample();
        example4.run();
    }

    static String currentTime() {
        return LocalDateTime.now().toLocalTime().toString();
    }
}
