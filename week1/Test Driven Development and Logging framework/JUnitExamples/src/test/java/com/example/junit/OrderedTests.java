package com.example.junit;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
public class OrderedTests {
    private static StringBuilder trace = new StringBuilder();

    @Test
    @Order(1)
    void stepOne() {
        trace.append("1");
    }

    @Test
    @Order(2)
    void stepTwo() {
        trace.append("2");
    }

    @Test
    @Order(3)
    void verifyOrder() {
        assertEquals("12", trace.toString());
    }
}
