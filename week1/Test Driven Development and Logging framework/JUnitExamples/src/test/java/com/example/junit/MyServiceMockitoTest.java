package com.example.junit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MyServiceMockitoTest {

    @Mock
    ExternalApi mockApi;

    @InjectMocks
    MyService service;

    @Test
    void testMockingAndStubbing() {
        when(mockApi.getData()).thenReturn("Mock Data");
        String result = service.fetchData();
        assertEquals("Mock Data", result);
    }

    @Test
    void testVerifyInteraction() {
        when(mockApi.getData()).thenReturn("x");
        service.fetchData();
        verify(mockApi).getData();
    }

    @Test
    void testArgumentMatching() {
        when(mockApi.getDataWithArg(anyString())).thenReturn("OK");
        String r = service.fetchDataWithArg("hello");
        assertEquals("OK", r);
        verify(mockApi).getDataWithArg(eq("hello"));
    }

    @Test
    void testVoidMethodHandling() {
        // default doNothing behavior; ensure no exception
        doNothing().when(mockApi).voidMethod();
        service.doVoid();
        verify(mockApi).voidMethod();
    }

    @Test
    void testMultipleReturns() {
        when(mockApi.getNext()).thenReturn("A", "B", "C");
        String r = service.consecutive();
        assertEquals("A,B", r);
        verify(mockApi, times(2)).getNext();
    }

    @Test
    void testVerifyOrder() {
        service.callInOrder();
        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).first();
        inOrder.verify(mockApi).second();
    }

    @Test
    void testVoidMethodThrows() {
        doThrow(new RuntimeException("boom")).when(mockApi).voidMethod();
        assertThrows(RuntimeException.class, () -> service.voidThrows());
        verify(mockApi).voidMethod();
    }
}
