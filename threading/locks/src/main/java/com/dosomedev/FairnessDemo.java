package com.dosomedev;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

public class FairnessDemo implements Runnable {
    private final ReentrantLock lock = new ReentrantLock(true);

    public void lockTask() {
        String threadName = Thread.currentThread().getName();

        for (int i = 0; i < 10; i++) {
            lock.lock();
            try {
                IO.println(threadName + " acquired the lock.");
                Thread.sleep(100); // Simulate work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                IO.println(threadName + " released the lock.");
                lock.unlock();
            }
        }
    }

    public void synchronizedTask() {
        String threadName = Thread.currentThread().getName();

        for (int i = 0; i < 2; i++) {
            synchronized (this) {
                try {
                    IO.println(threadName + " acquired the lock.");
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                IO.println(threadName + " released the lock.");
            }
        }
    }

    @Override
    public void run() {
        var start = LocalDateTime.now();
        FairnessDemo demo = new FairnessDemo();
        final Runnable task = demo::lockTask;
        System.out.printf("%nLock fairness: [%s]%n", lock.isFair());
        activity(task);
        System.out.printf("Duration: %sms%n%n", Duration.between(start, LocalDateTime.now()).toMillis());

        IO.println("synchronized");
        start = LocalDateTime.now();
        final Runnable synchronizedTask = demo::synchronizedTask;
        activity(synchronizedTask);
        System.out.printf("Duration: %sms%n", Duration.between(start, LocalDateTime.now()).toMillis());
    }

    private void activity(Runnable task) {
        Stream.of(
                        Thread.ofPlatform().name("Andre").start(task),
                        Thread.ofPlatform().name("Claus").start(task),
                        Thread.ofPlatform().name("Pawel").start(task)
                )
                .peek(Thread::start)
                .forEach(t -> {
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
