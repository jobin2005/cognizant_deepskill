package com.example.factory;

public class TestFactoryMethod {
    public static void main(String[] args) {
        DocumentFactory wordFactory = new WordDocumentFactory();
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        DocumentFactory excelFactory = new ExcelDocumentFactory();

        Document d1 = wordFactory.createDocument();
        Document d2 = pdfFactory.createDocument();
        Document d3 = excelFactory.createDocument();

        d1.open();
        d2.open();
        d3.open();
    }
}
