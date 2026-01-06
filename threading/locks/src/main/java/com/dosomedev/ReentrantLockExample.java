package com.dosomedev;

import lombok.Getter;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample implements Runnable {

    private final ReentrantLock lock = new ReentrantLock();

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

    @Override
    public void run() {
        int countUntil = 100000;
        ReentrantLockExample counter = new ReentrantLockExample();

        Thread thread1 = Thread.ofPlatform().name("t1").start(runnable(countUntil, counter));
        Thread thread2 = Thread.ofPlatform().name("t2").start(runnable(countUntil, counter));

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace(System.err);
        }

        IO.println("Count: " + counter.getCount());
    }

    private void printLockDetails(final Thread thread) {
        System.out.printf("Thread: %s%n", thread.getName());
        System.out.printf("HoldCount: %d%n", lock.getHoldCount());
        System.out.printf("QueueLength: %d%n", lock.getQueueLength());
        System.out.printf("HasQueuedThread: %b%n", lock.hasQueuedThread(thread));
        System.out.printf("HasQueuedThreads: %b%n", lock.hasQueuedThreads());
        System.out.printf("Fair: %b%n", lock.isFair());
        System.out.printf("Locked: %b%n", lock.isLocked());
        System.out.printf("HeldByCurrentThread: %b%n", lock.isHeldByCurrentThread());
    }

    private Runnable runnable(final int countUntil, final ReentrantLockExample counter) {
        return () -> {
            for (int i = 0; i < countUntil; i++) {
                counter.increment();
            }
//            printLockDetails(Thread.currentThread());
        };
    }
}
