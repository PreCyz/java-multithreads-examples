package com.dosomedev;

import java.time.Duration;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MatrixMultiplicationBigExample implements Runnable {
    private final int nrows;
    private final int ncols;

    @Override
    public void run() {
        Matrix a = new Matrix(nrows, ncols).randomize();
        Matrix b = new Matrix(ncols, nrows).randomize();

        LocalDateTime start = LocalDateTime.now();
        Matrix c = multiply(a, b);
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        System.out.printf("Multiplication completed in %d.%d sec%n", duration.toSecondsPart(), duration.toMillisPart());
    }

    private Matrix multiply(Matrix a, Matrix b) {
        if (a.getCols() != b.getRows()) {
            throw new IllegalArgumentException("Rows and columns mismatch!");
        }

        Matrix result = new Matrix(a.getRows(), b.getCols());
        for (int i = 0; i < a.getRows(); i++) {
            for (int j = 0; j < b.getCols(); j++) {
                for (int k = 0; k < a.getCols(); k++) {
                    result.setValue(i, j, result.getValue(i, j) + a.getValue(i, k) * b.getValue(k, j));
                }
            }
        }
        return result;
    }
}
