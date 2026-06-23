package com.example.observer;

import java.util.ArrayList;
import java.util.List;

public class StockMarket implements Stock {
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    @Override
    public void deregister(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(String symbol, double price) {
        for (Observer o : observers) {
            o.update(symbol, price);
        }
    }

    // Simulate price update
    public void setPrice(String symbol, double price) {
        System.out.println("StockMarket: " + symbol + " price changed to " + price);
        notifyObservers(symbol, price);
    }
}
