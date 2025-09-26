package com.dosomedev;

public class App {
    static void main(String[] args) throws InterruptedException {
        runThreads();
        System.out.println("The race is done!\n===================\n");

        runRunnables();
        System.out.println("The 2nd race is done!");
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
        Thread andy = new Thread(new RunnableSprinter("Andre", 250, 2));
        andy.setName("Andre");
        Thread precyz = new Thread(new RunnableSprinter("Pawg", 500, 0));
        precyz.setName("Pawg");
        Thread santaClaus = new Thread(new RunnableSprinter("Claus", 150, 1));
        santaClaus.setName("Claus");

        andy.start();
        precyz.start();
        santaClaus.start();
        andy.join();
        precyz.join();
        santaClaus.join();
    }
}
