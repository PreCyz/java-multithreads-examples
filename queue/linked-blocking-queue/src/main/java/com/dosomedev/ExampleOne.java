package com.dosomedev;

import java.util.concurrent.LinkedBlockingQueue;

public class ExampleOne implements Runnable {



    @Override
    public void run() {
        IO.println("Running example one:");

        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

        // Producer.
        Thread producer = new Thread(() -> {
            try {
                queue.put("Task A");
                queue.put("Task B");
                queue.put("Task C");
            } catch (InterruptedException ex) {
                IO.println("Producer interrupted!");
            }
        });

        // Consumer.
        Thread consumer = new Thread(() -> {
            try {
                while (!queue.isEmpty()) {
                    String element = queue.take();
                    IO.println("Consuming " + element + ".");
                }
            } catch (InterruptedException ex) {
                IO.println("Consumer interrupted!");
            }
        });

        // Thread start.
        producer.start();
        consumer.start();
    }
}
