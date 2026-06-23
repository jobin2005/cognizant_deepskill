package com.example.junit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MultiReturnServiceTest {

    @Mock
    Repository mockRepo;

    @InjectMocks
    RepoService service;

    @Test
    void testServiceWithMultipleReturnValues() {
        when(mockRepo.getData()).thenReturn("First Mock Data").thenReturn("Second Mock Data");
        String firstResult = service.processData();
        String secondResult = service.processData();
        assertEquals("Processed First Mock Data", firstResult);
        assertEquals("Processed Second Mock Data", secondResult);
    }
}
