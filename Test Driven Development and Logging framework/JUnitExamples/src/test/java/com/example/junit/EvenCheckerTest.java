package com.example.junit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class EvenCheckerTest {
    private final EvenChecker checker = new EvenChecker();

    @ParameterizedTest
    @ValueSource(ints = {0, 2, -4, 100})
    void testIsEven_true(int n) {
        assertTrue(checker.isEven(n));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, -3, 5, 101})
    void testIsEven_false(int n) {
        assertFalse(checker.isEven(n));
    }
}
