package com.dosomedev.sort;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SortExample implements Runnable {
    private final int tableSize;

    @Override
    public void run() {
        LocalDateTime start = LocalDateTime.now();
        var random = new Random();
        int[] data = new int[tableSize];
        for (int i = 0; i < tableSize; i++) {
            data[i] = random.nextInt(tableSize);
        }
        Duration duration = Duration.between(start, LocalDateTime.now());
        System.out.printf("Random table created in %d.%dsec%n", duration.toSecondsPart(), duration.toMillisPart());
        System.out.printf("Table[first]=%d, Table[last]=%d%n", data[0], data[data.length - 1]);

        try (ForkJoinPool pool = ForkJoinPool.commonPool()) {
            System.out.printf("Pool parallelism: %d%n", pool.getParallelism());

            QuickSortAction task = new QuickSortAction(data, 0, tableSize - 1);
            start = LocalDateTime.now();
            //`invoke` blocks program
            pool.invoke(task);

            //`submit` doesn't block program
//            submit(pool, task);

            //'execute' doesn't block program - async execution
//            execute(pool, task);

            //according to the specification: if you use commonPool() then Any program that relies on asynchronous task
            //processing to complete before program termination should invoke commonPool().awaitQuiescence, before exit
            pool.awaitQuiescence(10, TimeUnit.SECONDS);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            System.out.printf("Sorted: Table[first]=%d, Table[last]=%d%n", data[0], data[data.length - 1]);
            duration = Duration.between(start, LocalDateTime.now());
            System.out.printf("ForkJoinPool completed in %d.%dsec%n", duration.toSecondsPart(), duration.toMillisPart());
        }

    }

    private void submit(ForkJoinPool pool, ForkJoinTask<Void> task) throws InterruptedException {
        ForkJoinTask<Void> submit = pool.submit(task);
        printPoolDetails("submit", pool, submit);
        submit.join();
    }

    private void printPoolDetails(String operation, ForkJoinPool pool, ForkJoinTask<Void> task) throws InterruptedException {
        System.out.printf("%s QueuedSubmissionCount: %d%n", operation, pool.getQueuedSubmissionCount());

        //this blocks the program
        while (!task.isDone()) {
            System.out.printf("ActiveThreadCount: %d, ", pool.getActiveThreadCount());
            System.out.printf("QueuedTaskCount: %d, ", pool.getQueuedTaskCount());
            System.out.printf("StealCount: %d%n", pool.getStealCount());
            Thread.sleep(Duration.ofMillis(100));
        }
    }

    private void execute(ForkJoinPool pool, ForkJoinTask<Void> task) throws InterruptedException {
        pool.execute(task);
        printPoolDetails("execute", pool, task);
        task.join();
    }
}
