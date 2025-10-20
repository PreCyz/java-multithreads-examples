package com.dosomedev;

import java.util.WeakHashMap;

public class App {



    public static void main(String[] args) {

        WeakHashMap<Object, String> map = new WeakHashMap<>();

        Object key1 = new Object();
        Object key2 = new Object();

        map.put(key1, "Value for key1");
        map.put(key2, "Value for key2");

        IO.println("Map size: " + map.size());

        key1 = null;

        IO.println("Map size before GC: " + map.size());

        System.gc();

        IO.println("Map size after GC: " + map.size());

        try {
            Thread.sleep(1000);
        } catch (Exception ex) {
            IO.println(ex.getMessage());
        }

        IO.println("Map size after GC: " + map.size());

        IO.println(map.get(key2));
    }
}
