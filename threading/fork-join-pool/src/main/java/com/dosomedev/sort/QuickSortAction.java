package com.dosomedev.sort;

import java.util.concurrent.RecursiveAction;

public class QuickSortAction extends RecursiveAction {

    private static final int THRESHOLD = 1000;
    private final int[] array;
    private final int low;
    private final int high;

    public QuickSortAction(int[] array, int low, int high) {
        this.array = array;
        this.low = low;
        this.high = high;
    }

    @Override
    protected void compute() {
        if (low < high) {
            if (high - low < THRESHOLD) {
                quickSortSequential(array, low, high);
            } else {
                int pivotIndex = partition(array, low, high);

                QuickSortAction leftTask = new QuickSortAction(array, low, pivotIndex - 1);
                QuickSortAction rightTask = new QuickSortAction(array, pivotIndex + 1, high);

                // `invokeAll` fork all tasks at once
                // i czeka na ich zakończenie (`join`)
                invokeAll(leftTask, rightTask);
            }
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // Take last element as pivot
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);

        return i + 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void quickSortSequential(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSortSequential(arr, low, pivotIndex - 1);
            quickSortSequential(arr, pivotIndex + 1, high);
        }
    }
}
