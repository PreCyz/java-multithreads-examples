package com.dosomedev;

import java.util.concurrent.SynchronousQueue;

public class App {
    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<>();

        Thread producer = new Thread(() -> {
            try {
                IO.println("Producer: Hello");
                queue.put("Hello");
                IO.println("Producer: World");
                queue.put("World");
            } catch (InterruptedException ex) {
                System.err.println("Producer interrupted!");
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                String message1 = queue.take();
                IO.println("Consumer: " + message1);
                String message2 = queue.take();
                IO.println("Consumer: " + message2);
            } catch (InterruptedException ex) {
                System.err.println("Consumer interrupted!");
            }
        });

        producer.start();
        consumer.start();
    }
}
