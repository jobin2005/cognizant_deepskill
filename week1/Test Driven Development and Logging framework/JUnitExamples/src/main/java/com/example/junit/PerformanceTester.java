package com.example.junit;

public class PerformanceTester {
    public void performTask() {
        // simulate work
        try {
            Thread.sleep(50);
        } catch (InterruptedException ignored) {}
    }
}
