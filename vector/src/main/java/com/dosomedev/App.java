package com.dosomedev;

import java.util.Vector;

public class App {



    public static void main(String[] args) {
        Vector<String> names = new Vector<>();
        names.add("David");
        names.add("Jennifer");
        names.add("Elizabeth");
        IO.println("Vector values: " + names);
        IO.println("Name at index 1: " + names.get(1));
        IO.println("Getting values using for-loop:");
        for (int i = 0; i < names.size(); i++) {
            IO.println("   Value " + i + ": " + names.get(i));
        }

        names.set(1, "Rebecca");
        IO.println("Changed name at index 1: " + names);

        names.set(2, "Tony");
        IO.println("Changed name at index 2: " + names);

        names.remove(1);
        IO.println("Removed name at index 1: " + names);

        names.remove(1);
        IO.println("Removed name at index 1: " + names);

        names.clear();
        IO.println("Cleared all names: " + names);
    }
}
