package com.example.junit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FileServiceTest {

    @Mock
    IFFileReader mockReader;

    @Mock
    IFFileWriter mockWriter;

    @InjectMocks
    FileService service;

    @Test
    void testServiceWithMockFileIO() {
        when(mockReader.read()).thenReturn("Mock File Content");
        String result = service.processFile();
        assertEquals("Processed Mock File Content", result);
        verify(mockWriter).write("Processed Mock File Content");
    }
}
