package com.dosomedev;

import java.io.PrintStream;

public class Matrix {
    public static final PrintStream OUT = System.out;
    private final int[][] matrix;

    public Matrix(int nrows, int ncols) {
        matrix = new int[nrows][ncols];
    }

    public int getCols() {
        return matrix[0].length;
    }

    public int getRows() {
        return matrix.length;
    }

    public int getValue(int row, int col) {
        return matrix[row][col];
    }

    public void setValue(int row, int col, int value) {
        matrix[row][col] = value;
    }

    public static void dump(Matrix m) {
        for (int i = 0; i < m.getRows(); i++) {
            for (int j = 0; j < m.getCols(); j++) {
                OUT.printf("%d ", m.getValue(i, j));
            }
            OUT.println();
        }
        OUT.println();
    }
}
