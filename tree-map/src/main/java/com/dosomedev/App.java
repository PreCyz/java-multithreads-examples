package com.dosomedev;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class App {

    public static final PrintStream OUT = System.out;

    public static void main(String[] args) {

        TreeMap<String, Integer> words = new TreeMap<>();

        words.put("we", 1);
        words.put("of", 2);
        words.put("that", 3);
        words.put("these", 2);
        words.put("the", 2);

        Integer wordOf = words.get("of");
        OUT.println("Word count for 'of': " + wordOf);

        Integer wordAre = words.get("are");
        OUT.println("Word count for 'are': " + wordAre);

        if (words.containsKey("of")) {
            OUT.println("Word 'of' exists.");
        } else {
            OUT.println("Word 'of' does not exist.");
        }

        if (words.containsKey("are")) {
            OUT.println("Word 'are' exists.");
        } else {
            OUT.println("Word 'are' does not exist.");
        }

        OUT.println("Number of words (before removal): " + words.size());
        words.remove("of");
        OUT.println("Number of words (after removal): " + words.size());

        OUT.println("List of words (key : value):");
        for (Map.Entry<String, Integer> word : words.entrySet()) {
            OUT.println("- " + word.getKey() + " : " + word.getValue());
        }

        Comparator<String> comparatorByCount = Comparator.comparingInt(String::length);
        TreeMap<String, Integer> wordsWithComparator = new TreeMap<>(comparatorByCount);

        wordsWithComparator.put("we", 1);
        wordsWithComparator.put("of", 2);
        wordsWithComparator.put("that", 3);
        wordsWithComparator.put("these", 2);
        wordsWithComparator.put("the", 2);

        OUT.println("List of words (key : value) with custom comparator:");
        for (Map.Entry<String, Integer> word : wordsWithComparator.entrySet()) {
            OUT.println("- " + word.getKey() + " : " + word.getValue());
        }
    }
}
