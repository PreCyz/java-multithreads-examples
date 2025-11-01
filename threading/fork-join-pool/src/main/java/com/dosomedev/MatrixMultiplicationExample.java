package com.dosomedev;

import java.time.Duration;
import java.time.LocalDateTime;

public class MatrixMultiplicationExample implements Runnable {
    @Override
    public void run() {
        Matrix a = new Matrix(2, 3);
        a.setValue(0, 0, 1);
        a.setValue(0, 1, 2);
        a.setValue(0, 2, 3);
        a.setValue(1, 0, 4);
        a.setValue(1, 1, 5);
        a.setValue(1, 2, 6);
//        Matrix.dump(a);

        Matrix b = new Matrix(3, 2);
        b.setValue(0, 0, 7);
        b.setValue(1, 0, 8);
        b.setValue(2, 0, 9);
        b.setValue(0, 1, 1);
        b.setValue(1, 1, 2);
        b.setValue(2, 1, 3);
//        Matrix.dump(b);

        LocalDateTime start = LocalDateTime.now();
        // Multiply in this class.
        Matrix c = multiply(a, b);
        LocalDateTime end = LocalDateTime.now();
//        Matrix.dump(c);
        IO.println("Multiplication completed in " + Duration.between(start, end).toMillis() + "ms");
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
