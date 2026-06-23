package com.example.spring;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class ParameterizedCalculatorTest {
    private final CalculatorService service = new CalculatorService();

    @ParameterizedTest
    @CsvSource({"1,1,2", "2,3,5", "10,5,15"})
    void testAddParameterized(int a, int b, int expected) {
        assertEquals(expected, service.add(a, b));
    }
}
