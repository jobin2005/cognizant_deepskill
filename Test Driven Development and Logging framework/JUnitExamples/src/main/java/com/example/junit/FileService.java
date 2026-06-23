package com.example.junit;

public class FileService {
    private final IFFileReader reader;
    private final IFFileWriter writer;

    public FileService(IFFileReader reader, IFFileWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public String processFile() {
        String content = reader.read();
        String processed = "Processed " + content;
        writer.write(processed);
        return processed;
    }
}
