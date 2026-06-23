package com.example.observer;

public class MobileApp implements Observer {
    private final String name;

    public MobileApp(String name) {
        this.name = name;
    }

    @Override
    public void update(String symbol, double price) {
        System.out.println("MobileApp[" + name + "]: " + symbol + " -> " + price);
    }
}
