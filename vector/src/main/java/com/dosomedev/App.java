package com.dosomedev;

import java.io.PrintStream;
import java.util.Vector;

public class App {

    public static final PrintStream OUT = System.out;

    public static void main(String[] args) {
        Vector<String> names = new Vector<>();
        names.add("David");
        names.add("Jennifer");
        names.add("Elizabeth");
        OUT.println("Vector values: " + names);
        OUT.println("Name at index 1: " + names.get(1));
        OUT.println("Getting values using for-loop:");
        for (int i = 0; i < names.size(); i++) {
            OUT.println("   Value " + i + ": " + names.get(i));
        }

        names.set(1, "Rebecca");
        OUT.println("Changed name at index 1: " + names);

        names.set(2, "Tony");
        OUT.println("Changed name at index 2: " + names);

        names.remove(1);
        OUT.println("Removed name at index 1: " + names);

        names.remove(1);
        OUT.println("Removed name at index 1: " + names);

        names.clear();
        OUT.println("Cleared all names: " + names);
    }
}
