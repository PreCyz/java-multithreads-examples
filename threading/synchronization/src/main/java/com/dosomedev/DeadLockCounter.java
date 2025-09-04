package com.dosomedev;

import java.io.PrintStream;

public class DeadLockCounter {
    public static final PrintStream OUT = System.out;
    private long counterA = 0;

    private long counterB = 0;

    private final Object lockA = new Object();

    private final Object lockB = new Object();

    public void incrementA() {
        synchronized (lockA) {
            synchronized (lockB) {
                this.counterA++;
                OUT.println("First lock: lockA, second lock: lockB - counterA: " + this.counterA);
            }
        }
    }

    public void incrementB() {
        synchronized (lockB) {
            synchronized (lockA) {
                this.counterB++;
                OUT.println("First lock: lockB, second lock: lockA - counterB: " + this.counterB);
            }
        }
    }
}
