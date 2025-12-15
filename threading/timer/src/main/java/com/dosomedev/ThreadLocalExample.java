package com.dosomedev;

public class ThreadLocalExample implements Runnable {
    private static volatile ThreadLocal<String> userID = new ThreadLocal<>();

    @Override
    public void run() {
        Runnable r = () -> {
            String name = Thread.currentThread().getName();

            switch (name) {
                case "A":
                    userID.set("Andrzej");
                    break;
                case "C":
                    userID.set("Claus");
                    break;
                default:
                    userID.set("Pawg");
                    break;
            }

            System.out.printf("Thread name: %s, ThreadLocal value: %s%n", name, userID.get());
            userID.remove();
        };

        Thread.ofPlatform().name("A").start(r);
        Thread.ofPlatform().name("C").start(r);
        Thread.ofPlatform().start(r);
    }
}
