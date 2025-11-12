package com.dosomedev;

import com.dosomedev.sort.SortExample;

public class App {
    static void main(String[] args) {
        IO.println("\n========== MatrixMultiplicationExample ===============\n");
        new MatrixMultiplicationExample().run();
        new MatrixMultiplicationForkJoinExample().run();

        IO.println("\n========== MatrixMultiplicationBigExample ============\n");
        int nrows = 2000;
        int ncols = 200;
        new MatrixMultiplicationBigExample(nrows, ncols).run();
        new MatrixMultiplicationForkJoinBigExample(ncols, nrows).run();

        IO.println("\n========== SortExample ===============================\n");
        new SortExample(400_000_000).run();
    }
}
