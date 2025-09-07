package com.dosomedev;

import java.io.PrintStream;
import java.util.EnumMap;
import java.util.HashMap;

public class App {

    public static final PrintStream OUT = System.out;

    public static void main(String[] args) {
        // Define EnumMap.
        EnumMap<DayOfWeek, Double> temperatures = new EnumMap<>(DayOfWeek.class);

        // Add values.
        temperatures.put(DayOfWeek.MONDAY, 25.0);
        temperatures.put(DayOfWeek.TUESDAY, 28.0);
        temperatures.put(DayOfWeek.WEDNESDAY, 31.2);

        // Get value.
        Double mondayTemp = temperatures.get(DayOfWeek.MONDAY);
        OUT.println("Temperature on Monday: " + mondayTemp);

        // Check value.
        boolean tuesdayHasTemperature = temperatures.containsKey(DayOfWeek.TUESDAY);
        OUT.println("Does Tuesday have a temperature? " + (tuesdayHasTemperature ? "yes" : "no"));

        // Remove key-value pair.
        OUT.println();
        int sizeBeforeRemoval = temperatures.size();
        temperatures.remove(DayOfWeek.WEDNESDAY);
        int sizeAfterRemoval = temperatures.size();
        OUT.println("EnumMap size before removal: " + sizeBeforeRemoval);
        OUT.println("EnumMap size after removal:  " + sizeAfterRemoval);

        // Loop over all entries.
        OUT.println();
        OUT.println("List of all entries:");
        for (DayOfWeek day : temperatures.keySet()) {
            double temp = temperatures.get(day);
            OUT.println("- Temperature on " + day + ": " + temp);
        }

        // Clear the EnumMap.
        OUT.println();
        int sizeBeforeClear = temperatures.size();
        temperatures.clear();
        int sizeAfterClear = temperatures.size();
        OUT.println("EnumMap size before clear: " + sizeBeforeClear);
        OUT.println("EnumMap size after clear:  " + sizeAfterClear);

        // Performance test between EnumMap and HashMap.
        OUT.println();
        EnumMap<DayOfWeek, Integer> enumMap = new EnumMap<>(DayOfWeek.class);
        HashMap<DayOfWeek, Integer> hashMap = new HashMap<>();
        int numTests = 100000000;
        OUT.printf("Performance test (quantity %,d) between EnumMap and HashMap:%n", numTests);

        // Fill maps with data.
        for (DayOfWeek day : DayOfWeek.values()) {
            enumMap.put(day, day.ordinal());
            hashMap.put(day, day.ordinal());
        }

        // Measure EnumMap speed.
        long enumMapStart = System.nanoTime();
        for (int i = 0; i < numTests; i++) {
            for (DayOfWeek day : DayOfWeek.values()) {
                enumMap.get(day);
            }
        }
        long enumMapEnd = System.nanoTime();

        // Measure HashMap speed.
        long hashMapStart = System.nanoTime();
        for (int i = 0; i < numTests; i++) {
            for (DayOfWeek day : DayOfWeek.values()) {
                hashMap.get(day);
            }
        }
        long hashMapEnd = System.nanoTime();

        // Calculate results.
        long enumMapTime = enumMapEnd - enumMapStart;
        long hashMapTime = hashMapEnd - hashMapStart;
        OUT.println("EnumMap time: " + enumMapTime / numTests + " ms");
        OUT.println("HashMap time: " + hashMapTime / numTests + " ms");
    }
}
