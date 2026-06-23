package com.example.junit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NetworkServiceTest {

    @Mock
    NetworkClient mockClient;

    @InjectMocks
    NetworkService service;

    @Test
    void testServiceWithMockNetworkClient() {
        when(mockClient.connect()).thenReturn("Mock Connection");
        String result = service.connectToServer();
        assertEquals("Connected to Mock Connection", result);
    }
}
