package com.dosomedev;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
    private static final int NUMBER_OF_TASKS = 1000;

    private static final int CPU_CORES = Runtime.getRuntime().availableProcessors();

    private static final int PRIME_NUMBER = 1000000000;

    static void main(String[] args) {
        String input = IO.readln("Enter [1] for IO Test or [2] for CPU Test: ");

        if (input.equalsIgnoreCase("1")) {
            runIOTest("IO (traditional): ", Executors.newFixedThreadPool(CPU_CORES));
            runIOTest("IO (virtual):     ", Executors.newVirtualThreadPerTaskExecutor());
        } else if (input.equalsIgnoreCase("2")) {
            runCpuTest("CPU (traditional): ", Executors.newFixedThreadPool(CPU_CORES));
            runCpuTest("CPU (virtual):     ", Executors.newVirtualThreadPerTaskExecutor());
        } else {
            System.err.println("Unknown selection [" + input + "]");
        }
    }

    private static void runIOTest(String name, ExecutorService executor) {
        try {
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < NUMBER_OF_TASKS; i++) {
                executor.submit(new IoBoundTask(i));
            }

            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);

            long endTime = System.currentTimeMillis();

            IO.println(name + ": " + (endTime - startTime) + "ms");
        } catch (InterruptedException ex) {
            System.err.println("Error: interrupted!");
        }
    }

    private static void runCpuTest(String name, ExecutorService executor) {
        try {
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < NUMBER_OF_TASKS; i++) {
                executor.submit(new CpuBoundTask(PRIME_NUMBER));
            }

            // Wait for termination.
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);

            long endTime = System.currentTimeMillis();

            // Output statistics.
            IO.println(name + ": " + (endTime - startTime) + "ms");
        } catch (InterruptedException ex) {
            System.err.println("Error: interrupted!");
        }
    }
}
