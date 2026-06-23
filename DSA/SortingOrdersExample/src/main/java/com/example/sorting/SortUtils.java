package com.example.sorting;

import java.util.Arrays;

public class SortUtils {
    public static void bubbleSort(Order[] orders) {
        if (orders == null) return;
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (orders[j].getTotalPrice() > orders[j+1].getTotalPrice()) {
                    Order tmp = orders[j];
                    orders[j] = orders[j+1];
                    orders[j+1] = tmp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void quickSort(Order[] orders) {
        if (orders == null) return;
        quickSort(orders, 0, orders.length - 1);
    }

    private static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int p = partition(orders, low, high);
            quickSort(orders, low, p - 1);
            quickSort(orders, p + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() <= pivot) {
                i++;
                Order tmp = orders[i];
                orders[i] = orders[j];
                orders[j] = tmp;
            }
        }
        Order tmp = orders[i+1];
        orders[i+1] = orders[high];
        orders[high] = tmp;
        return i + 1;
    }

    public static Order[] copy(Order[] src) {
        return Arrays.copyOf(src, src.length);
    }
}
