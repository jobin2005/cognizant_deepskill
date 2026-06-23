package com.example.junit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RepoServiceTest {

    @Mock
    Repository mockRepo;

    @InjectMocks
    RepoService service;

    @Test
    void testServiceWithMockRepository() {
        when(mockRepo.getData()).thenReturn("Mock Data");
        String result = service.processData();
        assertEquals("Processed Mock Data", result);
    }
}
