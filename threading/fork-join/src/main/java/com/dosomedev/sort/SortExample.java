package com.dosomedev.sort;

import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

@RequiredArgsConstructor
public class SortExample implements Runnable {
    private final int tableSize;

    @Override
    public void run() {
        var random = new Random();
        int[] data = new int[tableSize];
        for (int i = 0; i < tableSize; i++) {
            data[i] = random.nextInt(tableSize);
        }
        System.out.printf("table before: first [%d], last [%d]%n", data[0], data[data.length - 1]);

        try (ForkJoinPool pool = ForkJoinPool.commonPool()) {
            System.out.printf("Pool parallelism: %d%n", pool.getParallelism());

            QuickSortAction task = new QuickSortAction(data, 0, tableSize - 1);
            // `invoke` blocks program
//            pool.invoke(task);
            ForkJoinTask<Void> submit = pool.submit(task);
            System.out.printf("QueuedSubmissionCount: %d%n", pool.getQueuedSubmissionCount());

            while (!submit.isDone()) {
                System.out.printf("ActiveThreadCount: %d, ", pool.getActiveThreadCount());
                System.out.printf("QueuedTaskCount: %d, ", pool.getQueuedTaskCount());
                System.out.printf("StealCount: %d%n", pool.getStealCount());
                Thread.sleep(Duration.ofMillis(100));
            }

            submit.join();

            pool.shutdown();

        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        } finally {
            System.out.printf("table after: first [%d], last [%d]%n", data[0], data[data.length - 1]);
        }

    }
}
