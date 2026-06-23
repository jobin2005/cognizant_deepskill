package com.example.sorting;

import java.util.Arrays;

public class TestSorting {
    public static void main(String[] args) {
        Order[] orders = new Order[] {
                new Order("O100","Alice", 250.0),
                new Order("O101","Bob", 50.0),
                new Order("O102","Charlie", 1250.0),
                new Order("O103","Diana", 499.99),
                new Order("O104","Eve", 10.0)
        };

        System.out.println("Original orders: " + Arrays.toString(orders));

        Order[] a1 = SortUtils.copy(orders);
        long t1 = System.nanoTime();
        SortUtils.bubbleSort(a1);
        long t2 = System.nanoTime();
        System.out.println("After Bubble Sort: " + Arrays.toString(a1) + ", time(ns): " + (t2 - t1));

        Order[] a2 = SortUtils.copy(orders);
        long t3 = System.nanoTime();
        SortUtils.quickSort(a2);
        long t4 = System.nanoTime();
        System.out.println("After Quick Sort: " + Arrays.toString(a2) + ", time(ns): " + (t4 - t3));

        System.out.println();
        System.out.println("Analysis:");
        System.out.println("- Bubble Sort: O(n^2) average/worst, O(n) best (already sorted). Not suitable for large datasets.");
        System.out.println("- Quick Sort: O(n log n) average, O(n^2) worst (rare with bad pivots). Generally preferred for large datasets.");
    }
}
