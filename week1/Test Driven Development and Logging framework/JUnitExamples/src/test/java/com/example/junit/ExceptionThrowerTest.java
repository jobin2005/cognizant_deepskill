package com.example.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExceptionThrowerTest {
    @Test
    void testThrowsRuntimeException() {
        ExceptionThrower ex = new ExceptionThrower();
        assertThrows(RuntimeException.class, ex::throwException);
    }
}
