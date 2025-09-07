package com.dosomedev;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.IdentityHashMap;

public class App {

    public static final PrintStream OUT = System.out;

    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        IdentityHashMap<String, String> identityHashMap = new IdentityHashMap<>();

        String str1 = new String("Hello");
        String str2 = new String("Hello");

        hashMap.put(str1, "World");
        identityHashMap.put(str1, "World");

        OUT.println("HashMap (str1): " + hashMap.get(str1)); // Output: World
        OUT.println("HashMap (str2): " + hashMap.get(str2)); // Output: World
        OUT.println("IdentityHashMap (str1): " + identityHashMap.get(str1)); // Output: World
        OUT.println("IdentityHashMap (str2): " + identityHashMap.get(str2)); // Output: null (IdentityHashMap uses reference equality)
        OUT.println("IdentityHashCode str1: " + System.identityHashCode(str1));
        OUT.println("IdentityHashCode str2: " + System.identityHashCode(str2));
    }
}
