package com.dosomedev;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ForkJoinPool;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MatrixMultiplicationForkJoinBigExample implements Runnable {
    private final int nrows;
    private final int ncols;

    @Override
    public void run() {
        Matrix a = new Matrix(nrows, ncols).randomize();
        Matrix b = new Matrix(ncols, nrows).randomize();

        LocalDateTime end = LocalDateTime.now();
        LocalDateTime start = LocalDateTime.now();
        Matrix c = new Matrix(a.getRows(), b.getCols());
        try (ForkJoinPool pool = ForkJoinPool.commonPool()) {
            pool.invoke(new MatrixMultiplicationForkJoin(a, b, c));
            end = LocalDateTime.now();
            pool.shutdown();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            Duration duration = Duration.between(start, end);
            System.out.printf("ForkJoinPool completed in %d.%d sec%n", duration.toSecondsPart(), duration.toMillisPart());
        }
    }
}
