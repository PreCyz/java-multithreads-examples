package com.dosomedev;

public class InheritableThreadLocalExample implements Runnable {
    private static final InheritableThreadLocal<Integer> intVal = new InheritableThreadLocal<>();

    @Override
    public void run() {
        Runnable parent = () -> {
            // Set inheritable thread local values.
            intVal.set(42);

            Runnable child = () -> {
                String name = Thread.currentThread().getName();

                System.out.printf("Thread name: %s, InheritableThreadLocal value: %d%n", name, intVal.get());
            };

            String name = Thread.currentThread().getName();

            System.out.printf("Thread name: %s, InheritableThreadLocal value: %d%n", name, intVal.get());

            Thread childThread = new Thread(child);
            childThread.setName("Child");
            childThread.start();
        };

        Thread parentThread = new Thread(parent);
        parentThread.setName("Parent");
        parentThread.start();
    }
}
