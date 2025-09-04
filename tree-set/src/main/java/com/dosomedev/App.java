package com.dosomedev;

import java.io.PrintStream;
import java.util.TreeSet;

public class App {

    public static final PrintStream OUT = System.out;

    public static void main(String[] args) {

        TreeSet<String> names = new TreeSet<>();

        names.add("Olivia");
        names.add("Noah");
        names.add("Charlotte");

        names.add("Olivia");

        if (names.contains("Riley")) {
            OUT.println("Riley is in the set.");
        } else {
            OUT.println("Riley is not in the set.");
        }

        OUT.println("Total names: " + names.size());

        names.remove("Noah");
        OUT.println("Removed Noah.");

        OUT.println("List of names:");
        for (String name : names) {
            OUT.println("- " + name);
        }

        names.clear();
        OUT.println("Emptied the set.");

        OUT.println("List of names:");
        for (String name : names) {
            OUT.println("- " + name);
        }
    }
}
