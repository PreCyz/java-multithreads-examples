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

        IO.println("Cat list based on likability:");
        while (!cats.isEmpty()) {
            IO.println(cats.poll());
        }

        IO.println("Cat list based on likability:");
        while (!cats.isEmpty()) {
            IO.println(cats.poll());
        }


        cats.add(new Cat("Marmalade", 10));
        cats.add(new Cat("Luna", 6));
        cats.add(new Cat("Claws", 0));
        cats.add(new Cat("Sherlock", 7));
        cats.add(new Cat("Scratchy", 2));

        IO.println("First cat in the list: " + cats.peek());
        IO.println("First cat in the list: " + cats.peek());
        IO.println("First cat in the list: " + cats.peek());


        cats.add(new Cat("Marmalade", 10));
        cats.add(new Cat("Luna", 6));
        cats.add(new Cat("Claws", 0));
        cats.add(new Cat("Sherlock", 7));
        cats.add(new Cat("Scratchy", 2));

        IO.println("First cat before clearing: " + cats.peek());
        IO.println("Cat quantity: " + cats.size());
        cats.clear();
        IO.println("First cat after clearing: " + cats.peek());
        IO.println("Cat quantity: " + cats.size());
    }
}
