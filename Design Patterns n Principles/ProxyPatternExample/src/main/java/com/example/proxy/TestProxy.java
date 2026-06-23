package com.example.proxy;

public class TestProxy {
    public static void main(String[] args) {
        Image img = new ProxyImage("http://example.com/image1.png");
        img.display();
        System.out.println("--- second call ---");
        img.display();
    }
}
