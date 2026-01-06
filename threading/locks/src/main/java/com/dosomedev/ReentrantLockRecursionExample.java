package com.dosomedev;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockRecursionExample implements Runnable {

    private final ReentrantLock lock = new ReentrantLock();

    public void recursion() {
        lock.lock();
        Thread thread = Thread.currentThread();
        System.out.printf("%nThread: %s enters recursion %d time(s)%n", thread.getName(), lock.getHoldCount());
        printLockDetails(thread);
        try {
            TimeUnit.SECONDS.sleep(2);
            if (lock.isLocked() && lock.getHoldCount() < 5) {
                recursion();
            } else {
                System.out.println(thread.getName() + " finished recursion.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace(System.err);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        ReentrantLockRecursionExample rlre = new ReentrantLockRecursionExample();
        Thread thread1 = Thread.ofPlatform().name("t1").start(rlre::recursion);
        Thread thread2 = Thread.ofPlatform().name("t2").start(rlre::recursion);

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace(System.err);
        } finally {
            IO.println();
            printLockDetails(Thread.currentThread());
        }
    }

    private void printLockDetails(final Thread thread) {
        System.out.printf("HoldCount: %d%n", lock.getHoldCount());
        System.out.printf("QueueLength: %d%n", lock.getQueueLength());
        System.out.printf("HasQueuedThread: %b%n", lock.hasQueuedThread(thread));
        System.out.printf("HasQueuedThreads: %b%n", lock.hasQueuedThreads());
        System.out.printf("Fair: %b%n", lock.isFair());
        System.out.printf("Locked: %b%n", lock.isLocked());
        System.out.printf("HeldByCurrentThread: %b%n", lock.isHeldByCurrentThread());
    }
}
