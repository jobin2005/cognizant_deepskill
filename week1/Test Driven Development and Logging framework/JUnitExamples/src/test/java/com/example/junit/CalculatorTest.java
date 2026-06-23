package com.example.junit;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {
    private Calculator calc;

    @Before
    public void setUp() {
        calc = new Calculator();
    }

    @Test
    public void testAdd() {
        // Arrange
        int a = 2, b = 3;
        // Act
        int result = calc.add(a, b);
        // Assert
        assertEquals(5, result);
    }

    @Test
    public void testSubtract() {
        assertEquals(1, calc.subtract(3, 2));
    }

    @Test
    public void testDivide() {
        assertEquals(2.5, calc.divide(5.0, 2.0), 1e-9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivideByZero() {
        calc.divide(1.0, 0.0);
    }
}
