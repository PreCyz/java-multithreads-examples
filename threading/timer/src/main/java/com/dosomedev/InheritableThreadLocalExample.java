package com.dosomedev;

public class InheritableThreadLocalExample implements Runnable {
    private static final InheritableThreadLocal<Integer> intVal = new InheritableThreadLocal<>();

    @Override
    public void run() {
        Runnable parent = () -> {
            intVal.set(42);

            Runnable child = () -> System.out.printf("Thread name: %s, InheritableThreadLocal value: %d%n",
                    Thread.currentThread().getName(),
                    intVal.get());

            System.out.printf("Thread name: %s, InheritableThreadLocal value: %d%n",
                    Thread.currentThread().getName(),
                    intVal.get());

            Thread.ofPlatform().name("Child").start(child);

            intVal.remove();
        };

        Thread.ofPlatform().name("Parent").start(parent);
    }
}
