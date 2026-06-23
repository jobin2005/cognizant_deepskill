package com.example.library;

import java.util.Arrays;

public class TestLibrary {
    public static void main(String[] args) {
        Book[] books = new Book[] {
                new Book("B001","Clean Code","Robert C. Martin"),
                new Book("B002","Design Patterns","Erich Gamma"),
                new Book("B003","Algorithms","Robert Sedgewick"),
                new Book("B004","Effective Java","Joshua Bloch"),
                new Book("B005","Refactoring","Martin Fowler")
        };

        String target = "Effective Java";

        long t1 = System.nanoTime();
        int idxLinear = LibrarySearch.linearSearch(books, target);
        long t2 = System.nanoTime();

        Book[] sorted = LibrarySearch.sortedByTitle(books);
        long t3 = System.nanoTime();
        int idxBinary = LibrarySearch.binarySearch(sorted, target);
        long t4 = System.nanoTime();

        System.out.println("Books (unsorted): " + Arrays.toString(books));
        System.out.println("Books (sorted): " + Arrays.toString(sorted));

        System.out.println("Linear search index: " + idxLinear + ", time(ns): " + (t2 - t1));
        System.out.println("Binary search index in sorted array: " + idxBinary + ", time(ns): " + (t4 - t3));

        System.out.println();
        System.out.println("Analysis:");
        System.out.println("- Linear search: O(n) time; simple and works on unsorted data.");
        System.out.println("- Binary search: O(log n) time; requires sorted data and random access (array).");
        System.out.println("- Use linear search for small or unsorted collections; use binary search or indexed structures for large collections.");
    }
}
