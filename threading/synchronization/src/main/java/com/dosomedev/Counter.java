package com.dosomedev;

import lombok.Getter;

@Getter
public class Counter {
    private final Object lock = new Object();
    private long number;

    public void incrementNotSync() {
        this.number++;
    }

    /** This method is equivalent to {@link #incrementSyncThis()} */
    public synchronized void incrementSyncMethod() {
        this.number++;
    }

    /** This method is equivalent to {@link #incrementSyncMethod()} */
    public void incrementSyncThis() {
        synchronized (this) {
            this.number++;
        }
    }

    /** This method is equivalent to {@link #incrementStaticSyncClass()} */
    public static synchronized void incrementStaticSyncMethod() {
    }

    /** This method is equivalent to {@link #incrementStaticSyncMethod()} */
    public static void incrementStaticSyncClass() {
        synchronized (Counter.class) {

        }
    }

    public void incrementSyncOnMonitorObject() {
        synchronized (lock) {
            this.number++;
        }
    }
}
