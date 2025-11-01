package com.dosomedev;

import com.dosomedev.sort.SortExample;

public class App {
    static void main(String[] args) {
        new MatrixMultiplicationExample().run();
        new MatrixMultiplicationForkJoinExample().run();
        IO.println("\n==================================================\n");

        int nrows = 200;
        int ncols = 200;
        new MatrixMultiplicationBigExample(nrows, ncols).run();
        new MatrixMultiplicationForkJoinBigExample(ncols, nrows).run();
        IO.println("\n==================================================\n");

        new SortExample(400_000_000).run();
    }
}
