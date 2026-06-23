package com.example.junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AAAPatternTest {
    private Calculator calc;

    @Before
    public void setup() {
        // Arrange: initialize shared fixture
        calc = new Calculator();
    }

    @After
    public void teardown() {
        // Teardown / cleanup
        calc = null;
    }

    @Test
    public void testMultiply_AAA() {
        // Act
        int result = calc.multiply(4, 5);
        // Assert
        assertEquals(20, result);
    }
}
