package com.example.spring;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorServiceTest {
    private final CalculatorService service = new CalculatorService();

    @Test
    void testAdd() {
        int result = service.add(2, 3);
        assertEquals(5, result);
    }
}
