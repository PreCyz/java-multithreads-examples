package com.dosomedev;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class App {
    static void main(String[] args) {
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
                TimeUnit.SECONDS.sleep(2);
                String message1 = queue.take();
                IO.println("Consumer: " + message1);

                TimeUnit.SECONDS.sleep(1);
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
