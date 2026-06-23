package com.example.search;

import java.util.Arrays;

public class TestSearch {
    public static void main(String[] args) {
        Product[] products = new Product[] {
                new Product("P100","Phone","Electronics"),
                new Product("P101","Laptop","Electronics"),
                new Product("P102","Shoes","Apparel"),
                new Product("P103","Watch","Accessories"),
                new Product("P104","Backpack","Accessories")
        };

        String target = "Laptop";

        long t1 = System.nanoTime();
        int idxLinear = SearchUtils.linearSearch(products, target);
        long t2 = System.nanoTime();

        Product[] sorted = SearchUtils.sortedByName(products);
        long t3 = System.nanoTime();
        int idxBinary = SearchUtils.binarySearch(sorted, target);
        long t4 = System.nanoTime();

        System.out.println("Products (unsorted): " + Arrays.toString(products));
        System.out.println("Products (sorted by name): " + Arrays.toString(sorted));

        System.out.println("Linear search index: " + idxLinear + ", time(ns): " + (t2 - t1));
        System.out.println("Binary search index in sorted array: " + idxBinary + ", time(ns): " + (t4 - t3));

        System.out.println();
        System.out.println("Analysis:");
        System.out.println("- Linear search: O(n) time, checks each element until match.");
        System.out.println("- Binary search: O(log n) time but requires sorted data and random access (array).\n");
    }
}
