package com.dosomedev;

import lombok.Getter;

@Getter
public class Counter {
    private final Object lock = new Object();
    private long number;

    public void increment() {
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


    /**
     * End of the synchronized block happens before entry into the subsequent synchronized block,
     * which is synchronized on the same monitor object.
     */
    public void incBy2SyncOnMonitorObject() {
        synchronized (lock) {
            this.number++;
        }

        System.out.print("");

        synchronized (lock) {
            this.number++;
        }
    }

    public synchronized void incWait() {
        increment();
    }
}
