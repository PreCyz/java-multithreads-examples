package com.dosomedev;

import java.io.PrintStream;
import java.util.concurrent.LinkedBlockingQueue;

public class ExampleOne implements Runnable {

    public static final PrintStream OUT = System.out;

    @Override
    public void run() {
        OUT.println("Running example one:");

        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

        // Producer.
        Thread producer = new Thread(() -> {
            try {
                queue.put("Task A");
                queue.put("Task B");
                queue.put("Task C");
            } catch (InterruptedException ex) {
                OUT.println("Producer interrupted!");
            }
        });

        // Consumer.
        Thread consumer = new Thread(() -> {
            try {
                while (!queue.isEmpty()) {
                    String element = queue.take();
                    OUT.println("Consuming " + element + ".");
                }
            } catch (InterruptedException ex) {
                OUT.println("Consumer interrupted!");
            }
        });

        // Thread start.
        producer.start();
        consumer.start();
    }
}
