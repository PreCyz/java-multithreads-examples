package com.dosomedev;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        IO.println("[1] for Blocking Queue");
        IO.println("[2] for Non-Blocking Queue");
        System.out.print("Enter a number: ");
        int choice = scanner.nextInt();

        scanner.close();

        ProducerConsumerQueue queue = null;
        switch (choice) {
            case 1:
                queue = new ProducerConsumerBlocking();
                break;
            case 2:
                queue = new ProducerConsumerNonBlocking();
                break;
            default:
                IO.println("Number not implemented.");
                break;
        }

        if (queue != null) {
            Thread producerThread = new Thread(queue::produce);
            Thread consumerThread = new Thread(queue::consume);

            producerThread.start();
            consumerThread.start();
        }
    }
}
