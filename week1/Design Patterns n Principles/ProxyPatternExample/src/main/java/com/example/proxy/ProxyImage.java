package com.example.proxy;

public class ProxyImage implements Image {
    private final String url;
    private RealImage realImage;

    public ProxyImage(String url) {
        this.url = url;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(url);
        } else {
            System.out.println("Using cached image for " + url);
        }
        realImage.display();
    }
}
