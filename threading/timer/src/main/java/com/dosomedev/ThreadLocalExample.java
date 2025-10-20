package com.dosomedev;

public class ThreadLocalExample implements Runnable {
    private static volatile ThreadLocal<String> userID = new ThreadLocal<>();

    @Override
    public void run() {
        Runnable r = () -> {
            String name = Thread.currentThread().getName();

            switch (name) {
                case "A":
                    userID.set("alpha");
                    break;
                case "B":
                    userID.set("beta");
                    break;
                default:
                    userID.set("nullus");
                    break;
            }

            System.out.printf("Thread name: %s, ThreadLocal value: %s%n", name, userID.get());
            userID.remove();
        };

        Thread.ofPlatform().name("A").start(r);
        Thread.ofPlatform().name("B").start(r);
        Thread.ofPlatform().start(r);
    }
}
