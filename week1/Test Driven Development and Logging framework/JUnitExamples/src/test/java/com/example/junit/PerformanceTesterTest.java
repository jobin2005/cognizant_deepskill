package com.example.junit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class PerformanceTesterTest {
    @Test
    @Timeout(1) // seconds
    void testPerformTask_timeout() {
        PerformanceTester p = new PerformanceTester();
        p.performTask();
    }

    @Test
    void testPerformTask_assertTimeout() {
        PerformanceTester p = new PerformanceTester();
        assertTimeout(Duration.ofSeconds(1), p::performTask);
    }
}
