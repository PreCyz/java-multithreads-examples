package com.dosomedev;

import java.io.PrintStream;
import java.util.concurrent.Exchanger;

public class ExchangerExample implements Runnable {

    public static final PrintStream OUT = System.out;

    @Override
    public void run() {
        Exchanger<String> exchanger = new Exchanger<>();

        Thread producer = new Thread(() -> {
            String buffer = "PPP";
            try {
                while (true) {
                    OUT.println("Producer sends:    " + buffer);
                    buffer = exchanger.exchange(buffer);
                    OUT.println("Producer receives: " + buffer);
                }
            } catch (InterruptedException ex) {
                System.err.println("Producer interrupted!");
            }
        });

        Thread consumer = new Thread(() -> {
            String buffer = "CCC";
            try {
                while (true) {
                    OUT.println("Consumer sends:    " + buffer);
                    buffer = exchanger.exchange(buffer);
                    OUT.println("Consumer receives: " + buffer);
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
            ex.printStackTrace(OUT);
        }

        producer.interrupt();
        consumer.interrupt();
    }
}
