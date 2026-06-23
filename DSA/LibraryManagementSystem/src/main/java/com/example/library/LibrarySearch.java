package com.example.library;

import java.util.Arrays;
import java.util.Comparator;

public class LibrarySearch {
    // Linear search by title (case-insensitive)
    public static int linearSearch(Book[] books, String title) {
        if (books == null || title == null) return -1;
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null && title.equalsIgnoreCase(books[i].getTitle())) return i;
        }
        return -1;
    }

    // Binary search by title (array must be sorted by title ascending)
    public static int binarySearch(Book[] sortedBooks, String title) {
        if (sortedBooks == null || title == null) return -1;
        int lo = 0, hi = sortedBooks.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            Book b = sortedBooks[mid];
            if (b == null) return -1;
            int cmp = title.compareToIgnoreCase(b.getTitle());
            if (cmp == 0) return mid;
            if (cmp < 0) hi = mid - 1; else lo = mid + 1;
        }
        return -1;
    }

    public static Book[] sortedByTitle(Book[] books) {
        Book[] copy = Arrays.copyOf(books, books.length);
        Arrays.sort(copy, Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER));
        return copy;
    }
}
