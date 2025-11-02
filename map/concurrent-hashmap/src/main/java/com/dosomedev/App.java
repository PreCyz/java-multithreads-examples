package com.dosomedev;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;

public class App {
    private static final int ROUNDS = 6;

    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();

    private static final int ITERATIONS = 1_000_000;

    static void main(String[] args) throws InterruptedException {
        mapBenchmark(ITERATIONS);
        IO.println("\n=================================================\n");
        listBenchmark(ITERATIONS / 100);
    }

    private static void mapBenchmark(int iterations) throws InterruptedException {
        // Control variables.
        long avgHashtableMapMillis = 0;
        long avgSynchronizedHashMapMillis = 0;
        long avgConcurrentHashMapMillis = 0;

        // Benchmark loop.
        for (int round = 1; round <= ROUNDS; round++) {
            // Define maps.
            Map<String, Integer> hashtableMap = new Hashtable<>();
            Map<String, Integer> synchronizedHashMap = Collections.synchronizedMap(new HashMap<>());
            Map<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();

            // Perform benchmarks.
            avgHashtableMapMillis += benchmarkMap(hashtableMap, round, iterations);
            avgSynchronizedHashMapMillis += benchmarkMap(synchronizedHashMap, round, iterations);
            avgConcurrentHashMapMillis += benchmarkMap(concurrentHashMap, round, iterations);

            IO.println();
        }

        // Calculate average millis.
        avgHashtableMapMillis /= ROUNDS;
        avgSynchronizedHashMapMillis /= ROUNDS;
        avgConcurrentHashMapMillis /= ROUNDS;

        // Print statistics.
        IO.println("Average Hashtable millis:            " + avgHashtableMapMillis + " ms");
        IO.println("Average synchronized HashMap millis: " + avgSynchronizedHashMapMillis + " ms");
        IO.println("Average ConcurrentHashMap millis:    " + avgConcurrentHashMapMillis + " ms");
    }

    private static long benchmarkMap(Map<String, Integer> map, int round, int iterations) throws InterruptedException {
        try (ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT)) {
            LocalDateTime start = LocalDateTime.now();

            for (int i = 0; i < THREAD_COUNT; i++) {
                executor.submit(() -> {
                    for (int j = 0; j < iterations; j++) {
                        String key = "thread " + j;
                        map.put(key, j);
                        map.get(key);
                    }
                });
            }

            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);

            Duration duration = Duration.between(start, LocalDateTime.now());
            System.out.printf("Round %d: %s elapsed time: %d ms%n",
                    round, map.getClass().getSimpleName(), duration.toMillis());

            return duration.toMillis();
        }
    }

    private static void listBenchmark(int iterations) throws InterruptedException {
        long avgCopyOnWriteListMillis = 0;
        long avgSynchronizedArrayListMillis = 0;
        long avgVectorMillis = 0;

        for (int round = 1; round <= ROUNDS; round++) {
            List<String> copyOnWriteList = new CopyOnWriteArrayList<>(new ArrayList<>(ITERATIONS));
            List<String> synchronizedArrayList = Collections.synchronizedList(new ArrayList<>(ITERATIONS));
            List<String> vector = new Vector<>(new ArrayList<>(ITERATIONS));

            // Perform benchmarks.
            avgCopyOnWriteListMillis += benchmarkList(copyOnWriteList, round, iterations);
            avgSynchronizedArrayListMillis += benchmarkList(synchronizedArrayList, round, iterations);
            avgVectorMillis += benchmarkList(vector, round, iterations);

            IO.println();
        }

        // Calculate average millis.
        avgCopyOnWriteListMillis /= ROUNDS;
        avgSynchronizedArrayListMillis /= ROUNDS;
        avgVectorMillis /= ROUNDS;

        // Print statistics.
        IO.println("Avg CopyOnWriteArrayList   : " + avgCopyOnWriteListMillis + " ms");
        IO.println("Avg synchronized ArrayList : " + avgSynchronizedArrayListMillis + " ms");
        IO.println("Avg Vector                 : " + avgVectorMillis + " ms");
    }

    private static long benchmarkList(List<String> list, int round, int iterations) throws InterruptedException {
        try (ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT)) {
            LocalDateTime start = LocalDateTime.now();

            for (int i = 0; i < THREAD_COUNT; i++) {
                executor.submit(() -> {
                    for (int j = 0; j < iterations; j++) {
                        String key = "thread " + j;
                        list.add(j, key);
                        list.get(j);
                    }
                });
            }

            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);

            Duration duration = Duration.between(start, LocalDateTime.now());
            System.out.printf("Round %d: %s elapsed time: %d ms%n",
                    round, list.getClass().getSimpleName(), duration.toMillis());

            return duration.toMillis();
        }
    }
}
