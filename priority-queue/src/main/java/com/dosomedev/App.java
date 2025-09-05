package com.dosomedev;

import java.util.PriorityQueue;

public class App {
    public static void main(String[] args) {
        PriorityQueue<Cat> cats = new PriorityQueue<>();
        cats.add(new Cat("Marmalade", 10));
        cats.add(new Cat("Luna", 6));
        cats.add(new Cat("Claws", 0));
        cats.add(new Cat("Sherlock", 7));
        cats.add(new Cat("Scratchy", 2));

        System.out.println("Cat list based on likability:");
        while (!cats.isEmpty()) {
            System.out.println(cats.poll());
        }

        System.out.println("Cat list based on likability:");
        while (!cats.isEmpty()) {
            System.out.println(cats.poll());
        }


        cats.add(new Cat("Marmalade", 10));
        cats.add(new Cat("Luna", 6));
        cats.add(new Cat("Claws", 0));
        cats.add(new Cat("Sherlock", 7));
        cats.add(new Cat("Scratchy", 2));

        System.out.println("First cat in the list: " + cats.peek());
        System.out.println("First cat in the list: " + cats.peek());
        System.out.println("First cat in the list: " + cats.peek());


        cats.add(new Cat("Marmalade", 10));
        cats.add(new Cat("Luna", 6));
        cats.add(new Cat("Claws", 0));
        cats.add(new Cat("Sherlock", 7));
        cats.add(new Cat("Scratchy", 2));

        System.out.println("First cat before clearing: " + cats.peek());
        System.out.println("Cat quantity: " + cats.size());
        cats.clear();
        System.out.println("First cat after clearing: " + cats.peek());
        System.out.println("Cat quantity: " + cats.size());
    }
}
