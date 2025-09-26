package com.dosomedev;

import java.util.TreeSet;

public class App {



    public static void main(String[] args) {

        TreeSet<String> names = new TreeSet<>();

        names.add("Olivia");
        names.add("Noah");
        names.add("Charlotte");

        names.add("Olivia");

        if (names.contains("Riley")) {
            IO.println("Riley is in the set.");
        } else {
            IO.println("Riley is not in the set.");
        }

        IO.println("Total names: " + names.size());

        names.remove("Noah");
        IO.println("Removed Noah.");

        IO.println("List of names:");
        for (String name : names) {
            IO.println("- " + name);
        }

        names.clear();
        IO.println("Emptied the set.");

        IO.println("List of names:");
        for (String name : names) {
            IO.println("- " + name);
        }
    }
}
