package com.example.employee;

import java.util.Arrays;

public class EmployeeArrayManager {
    private Employee[] arr;
    private int size;

    public EmployeeArrayManager(int capacity) {
        arr = new Employee[capacity];
        size = 0;
    }

    public boolean addEmployee(Employee e) {
        if (e == null) return false;
        if (size >= arr.length) return false; // no resize for simplicity
        arr[size++] = e;
        return true;
    }

    public Employee searchById(String id) {
        for (int i = 0; i < size; i++) {
            if (arr[i].getEmployeeId().equals(id)) return arr[i];
        }
        return null;
    }

    public boolean deleteById(String id) {
        for (int i = 0; i < size; i++) {
            if (arr[i].getEmployeeId().equals(id)) {
                // shift left
                for (int j = i; j < size - 1; j++) arr[j] = arr[j+1];
                arr[size-1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    public void traverse() {
        for (int i = 0; i < size; i++) System.out.println(arr[i]);
    }

    public int getSize() { return size; }

    public Employee[] toArray() { return Arrays.copyOf(arr, size); }
}
