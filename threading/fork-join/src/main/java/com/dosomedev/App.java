package com.dosomedev;

import com.dosomedev.sort.SortExample;

public class App {
    static void main(String[] args) {
        new MatrixMultiplicationExample().run();
        new MatrixMultiplicationForkJoinExample().run();

        int nrows = 2000;
        int ncols = 2000;
        new MatrixMultiplicationBigExample(nrows, ncols).run();
        new MatrixMultiplicationForkJoinBigExample(ncols, nrows).run();

        new SortExample(400_000_000).run();
    }
}
