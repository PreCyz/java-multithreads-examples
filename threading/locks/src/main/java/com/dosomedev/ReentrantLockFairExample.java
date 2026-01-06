package com.dosomedev;

import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockFairExample implements Runnable {

//    private final ReentrantLock lock = new ReentrantLock();
    private final ReentrantLock lock = new ReentrantLock(true);

    @Getter
    private int count;

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int get() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        var start = LocalDateTime.now();
        int countUntil = 1000;
        ReentrantLockFairExample counter = new ReentrantLockFairExample();

        Thread thread1 = Thread.ofPlatform().name("t1").start(increment(countUntil, counter));
        Thread thread2 = Thread.ofPlatform().name("t2").start(get(countUntil, counter));

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace(System.err);
        }

        IO.println("Count: " + counter.getCount());
        IO.println("Duration: " + Duration.between(start, LocalDateTime.now()).toMillis());
    }

    private Runnable increment(final int countUntil, final ReentrantLockFairExample counter) {
        return () -> {
            for (int i = 0; i < countUntil; i++) {
                counter.increment();
                IO.println("set i=" + (i + 1));
            }
        };
    }

    private Runnable get(final int countUntil, final ReentrantLockFairExample counter) {
        return () -> {
            for (int i = 0; i < countUntil; i++) {
                IO.println(counter.get());
            }
        };
    }
}
