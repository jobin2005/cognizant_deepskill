package com.example.junit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApiServiceTest {

    @Mock
    RestClient mockClient;

    @InjectMocks
    ApiService service;

    @Test
    void testServiceWithMockRestClient() {
        when(mockClient.getResponse()).thenReturn("Mock Response");
        String result = service.fetchData();
        assertEquals("Fetched Mock Response", result);
    }
}
