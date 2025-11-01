package com.dosomedev;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ForkJoinPool;

public class MatrixMultiplicationForkJoinExample implements Runnable {
    @Override
    public void run() {
        Matrix a = new Matrix(2, 3);
        a.setValue(0, 0, 1);
        a.setValue(0, 1, 2);
        a.setValue(0, 2, 3);
        a.setValue(1, 0, 4);
        a.setValue(1, 1, 5);
        a.setValue(1, 2, 6);
        Matrix.dump(a);

        Matrix b = new Matrix(3, 2);
        b.setValue(0, 0, 7);
        b.setValue(1, 0, 8);
        b.setValue(2, 0, 9);
        b.setValue(0, 1, 1);
        b.setValue(1, 1, 2);
        b.setValue(2, 1, 3);
        Matrix.dump(b);

        LocalDateTime end = LocalDateTime.now();
        LocalDateTime start = LocalDateTime.now();
        // Multiply using ForkJoinPool.
        Matrix c = new Matrix(a.getRows(), b.getCols());
        try (ForkJoinPool pool = new ForkJoinPool()) {
            pool.invoke(new MatrixMultiplicationForkJoin(a, b, c));
            end = LocalDateTime.now();
            Matrix.dump(c);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            IO.println("Completed in " + Duration.between(start, end).toMillis() + "ms");
        }
    }
}
