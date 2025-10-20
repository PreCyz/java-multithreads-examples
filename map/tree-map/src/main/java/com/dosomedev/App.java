package com.dosomedev;

import java.util.*;

public class App {



    public static void main(String[] args) {

        TreeMap<String, Integer> words = new TreeMap<>();

        words.put("we", 1);
        words.put("of", 2);
        words.put("that", 3);
        words.put("these", 2);
        words.put("the", 2);

        Integer wordOf = words.get("of");
        IO.println("Word count for 'of': " + wordOf);

        Integer wordAre = words.get("are");
        IO.println("Word count for 'are': " + wordAre);

        if (words.containsKey("of")) {
            IO.println("Word 'of' exists.");
        } else {
            IO.println("Word 'of' does not exist.");
        }

        if (words.containsKey("are")) {
            IO.println("Word 'are' exists.");
        } else {
            IO.println("Word 'are' does not exist.");
        }

        IO.println("Number of words (before removal): " + words.size());
        words.remove("of");
        IO.println("Number of words (after removal): " + words.size());

        IO.println("List of words (key : value):");
        for (Map.Entry<String, Integer> word : words.entrySet()) {
            IO.println("- " + word.getKey() + " : " + word.getValue());
        }

        Comparator<String> comparatorByCount = Comparator.comparingInt(String::length);
        TreeMap<String, Integer> wordsWithComparator = new TreeMap<>(comparatorByCount);

        wordsWithComparator.put("we", 1);
        wordsWithComparator.put("of", 2);
        wordsWithComparator.put("that", 3);
        wordsWithComparator.put("these", 2);
        wordsWithComparator.put("the", 2);

        IO.println("List of words (key : value) with custom comparator:");
        for (Map.Entry<String, Integer> word : wordsWithComparator.entrySet()) {
            IO.println("- " + word.getKey() + " : " + word.getValue());
        }
    }
}
