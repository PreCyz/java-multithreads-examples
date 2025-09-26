package com.dosomedev;

import java.util.HashMap;
import java.util.IdentityHashMap;

public class App {



    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        IdentityHashMap<String, String> identityHashMap = new IdentityHashMap<>();

        String str1 = new String("Hello");
        String str2 = new String("Hello");

        hashMap.put(str1, "World");
        identityHashMap.put(str1, "World");

        IO.println("HashMap (str1): " + hashMap.get(str1)); // Output: World
        IO.println("HashMap (str2): " + hashMap.get(str2)); // Output: World
        IO.println("IdentityHashMap (str1): " + identityHashMap.get(str1)); // Output: World
        IO.println("IdentityHashMap (str2): " + identityHashMap.get(str2)); // Output: null (IdentityHashMap uses reference equality)
        IO.println("IdentityHashCode str1: " + System.identityHashCode(str1));
        IO.println("IdentityHashCode str2: " + System.identityHashCode(str2));
    }
}
