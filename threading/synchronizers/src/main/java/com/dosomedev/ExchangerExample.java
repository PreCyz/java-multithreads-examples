package com.dosomedev;

import java.util.concurrent.Exchanger;

public class ExchangerExample implements Runnable {



    @Override
    public void run() {
        Exchanger<String> exchanger = new Exchanger<>();

        Thread producer = new Thread(() -> {
            String buffer = "PPP";
            try {
                while (true) {
                    IO.println("Producer sends:    " + buffer);
                    buffer = exchanger.exchange(buffer);
                    IO.println("Producer receives: " + buffer);
                }
            } catch (InterruptedException ex) {
                System.err.println("Producer interrupted!");
            }
        });

        Thread consumer = new Thread(() -> {
            String buffer = "CCC";
            try {
                while (true) {
                    IO.println("Consumer sends:    " + buffer);
                    buffer = exchanger.exchange(buffer);
                    IO.println("Consumer receives: " + buffer);
                }
            } catch (InterruptedException ex) {
                System.err.println("Consumer interrupted!");
            }
        });

        producer.start();
        consumer.start();

        try {
            Thread.sleep(5);
        } catch (InterruptedException ex) {
            ex.printStackTrace(System.err);
        }

        producer.interrupt();
        consumer.interrupt();
    }
}
