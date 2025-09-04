package com.dosomedev;

import java.io.PrintStream;
import java.util.WeakHashMap;

public class App {

    public static final PrintStream OUT = System.out;

    public static void main(String[] args) {

        WeakHashMap<Object, String> map = new WeakHashMap<>();

        Object key1 = new Object();
        Object key2 = new Object();

        map.put(key1, "Value for key1");
        map.put(key2, "Value for key2");

        OUT.println("Map size: " + map.size());

        key1 = null;

        OUT.println("Map size before GC: " + map.size());

        System.gc();

        OUT.println("Map size after GC: " + map.size());

        try {
            Thread.sleep(1000);
        } catch (Exception ex) {
            OUT.println(ex.getMessage());
        }

        OUT.println("Map size after GC: " + map.size());

        OUT.println(map.get(key2));
    }
}
