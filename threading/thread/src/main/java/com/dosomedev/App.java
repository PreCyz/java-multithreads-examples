package com.dosomedev;

public class App {
    static void main(String[] args) throws InterruptedException {
        runThreads();
        System.out.printf("The race is done!%n===================%n%n");

        runRunnables();
        System.out.printf("The 2nd race is done!%n===================%n%n");

        runThreadFactories();
        IO.println("The 3rd race is done!%n===================%n%n");
    }

    private static void runThreads() throws InterruptedException {
        Sprinter andre = new Sprinter("Andre", 250, 0);
        Sprinter me = new Sprinter("Pawg", 650, 3);
        Sprinter claus = new Sprinter("Claus", 150, 0);

        andre.start();
        andre.join();
        me.start();
        me.join();
        claus.start();
        claus.join();
    }

    private static void runRunnables() throws InterruptedException {
        Thread andre = new Thread(new RunnableSprinter("Andre", 250, 2));
        andre.setName("Andre");
        Thread me = new Thread(new RunnableSprinter("Pawg", 500, 0));
        me.setName("Pawg");
        Thread claus = new Thread(new RunnableSprinter("Claus", 150, 1));
        claus.setName("Claus");

        andre.start();
        me.start();
        claus.start();
        andre.join();
        me.join();
        claus.join();
    }

    private static void runThreadFactories() throws InterruptedException {

        Thread andre = Thread.ofPlatform()
                             .daemon()
                             .name("Andre")
                             .start(new RunnableSprinter("Andre", 10, 0));

        Thread me = Thread.ofPlatform()
                          .daemon()
                          .name("pawg")
                          .start(new RunnableSprinter("Pawg", 100, 3));

        Thread claus = Thread.ofPlatform()
                             .daemon()
                             .name("Claus")
                             .unstarted(new RunnableSprinter("Claus", 65, 0));

        andre.join();
        me.join();

        claus.start();
        claus.join();
    }
}
