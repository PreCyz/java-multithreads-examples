package com.dosomedev;

public class App {

    //TODO: 1. Sprinter as Thread
    //TODO: 2. RunnableSprinter
    //TODO: 3. Deamon vs User
    static void main(String[] args) throws InterruptedException {
        runThreadsInSequence();
        System.out.printf("The race is done!%n===================%n%n");

        runThreadsAllAtOnce();
        System.out.printf("The 2nd race is done!%n===================%n%n");

        deamonVsUserThreads();
        IO.println("The 3rd race is done!%n===================%n%n");
    }

    /**
     * Thread is started when the previous is finished.
     */
    private static void runThreadsInSequence() throws InterruptedException {
        Sprinter andre = new Sprinter("Andre", 250, 0);
        Sprinter pawg = new Sprinter("Pawg", 650, 3);
        Sprinter claus = new Sprinter("Claus", 150, 0);

        andre.start();
        andre.join();
        pawg.start();
        pawg.join();
        claus.start();
        claus.join();
    }

    /**
     * Thread are started at the same time (more/less). They're running at the simultaneously
     */
    private static void runThreadsAllAtOnce() throws InterruptedException {
        Thread andre = new Thread(new RunnableSprinter("Andre", 250, 0));
        andre.setName("Andre");
        Thread pawg = new Thread(new RunnableSprinter("Pawg", 500, 0));
        pawg.setName("Pawg");
        Thread claus = new Thread(new RunnableSprinter("Claus", 150, 0));
        claus.setName("Claus");

        andre.start();
        pawg.start();
        claus.start();
        andre.join();
        pawg.join();
        claus.join();
    }

    /**
     * Deamon vs User Thread
     * TODO: JVM Lifecycle is Key: User threads are "blocking"; daemon threads are not.
     * TODO: User Threads for Critical Work.
     * TODO: Daemon Threads for Background Services.
     * TODO: Beware the finally Block: Do not rely on finally blocks in daemon threads for resource cleanup. This is a common source of bugs.
     */
    private static void deamonVsUserThreads() throws InterruptedException {

        Thread andre = Thread.ofPlatform()
                             .daemon()
                             .name("Andre")
                             .start(new RunnableSprinter("Andre", 10, 0));

        //Non-deamon
        Thread pawg = Thread.ofPlatform()
                            .name("pawg")
                            .start(new RunnableSprinter("Pawg", 100, 0));

        Thread claus = Thread.ofPlatform()
                             .daemon()
                             .name("Claus")
                             .unstarted(new RunnableSprinter("Claus", 165, 0));

        andre.join();
        pawg.join();

        claus.start();
        //        claus.join(); // if you want this demon thread completed, uncomment this code,
        // otherwise, the result is going to be skipped.
    }
}
