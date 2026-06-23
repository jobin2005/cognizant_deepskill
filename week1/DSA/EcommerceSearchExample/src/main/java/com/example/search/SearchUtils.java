package com.example.search;

import java.util.Arrays;
import java.util.Comparator;

public class SearchUtils {
    // Linear search by product name (case-insensitive). Returns index or -1.
    public static int linearSearch(Product[] products, String name) {
        if (products == null || name == null) return -1;
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null && name.equalsIgnoreCase(products[i].getProductName())) {
                return i;
            }
        }
        return -1;
    }

    // Binary search by product name on a sorted array (ascending by name). Returns index or -1.
    public static int binarySearch(Product[] sortedProducts, String name) {
        if (sortedProducts == null || name == null) return -1;
        int lo = 0, hi = sortedProducts.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            Product p = sortedProducts[mid];
            if (p == null) return -1;
            int cmp = name.compareToIgnoreCase(p.getProductName());
            if (cmp == 0) return mid;
            if (cmp < 0) hi = mid - 1; else lo = mid + 1;
        }
        return -1;
    }

    // Helper to return a sorted copy by productName
    public static Product[] sortedByName(Product[] products) {
        Product[] copy = Arrays.copyOf(products, products.length);
        Arrays.sort(copy, Comparator.comparing(Product::getProductName, String.CASE_INSENSITIVE_ORDER));
        return copy;
    }
}
