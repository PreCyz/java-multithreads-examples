package com.dosomedev;

import java.util.concurrent.TimeUnit;

public class InheritableThreadLocalExample implements Runnable {
    private static final ThreadLocal<String> stringVal = new ThreadLocal<>();
    private static final InheritableThreadLocal<Integer> intVal = new InheritableThreadLocal<>();

    @Override
    public void run() {

        Thread.ofPlatform().name("Parent").start(() -> {
            stringVal.set("PAWG");
            intVal.set(42);

            System.out.printf("Thread name: %s, ThreadLocal value %s, InheritableThreadLocal value: %d%n",
                    Thread.currentThread().getName(),
                    stringVal.get(),
                    intVal.get()
            );



            Thread.ofPlatform().name("Child").start(() -> {
                System.out.printf("Thread name: %s, ThreadLocal value %s, InheritableThreadLocal value: %d%n",
                        Thread.currentThread().getName(),
                        stringVal.get(),
                        intVal.get()
                );
            });

        });

        Thread.ofPlatform().name("Zetor").start(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
            System.out.printf("Thread name: %s, ThreadLocal value %s, InheritableThreadLocal value: %d%n",
                    Thread.currentThread().getName(),
                    stringVal.get(),
                    intVal.get()
            );
        });
    }
}
