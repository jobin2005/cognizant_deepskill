package com.example.proxy;

public class RealImage implements Image {
    private final String url;

    public RealImage(String url) {
        this.url = url;
        loadFromRemote();
    }

    private void loadFromRemote() {
        System.out.println("Loading image from " + url + " ... (simulated)");
        try { Thread.sleep(300); } catch (InterruptedException ignored) {}
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + url);
    }
}
