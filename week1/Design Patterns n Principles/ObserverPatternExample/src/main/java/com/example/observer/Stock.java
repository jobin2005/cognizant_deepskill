package com.example.observer;

public interface Stock {
    void register(Observer o);
    void deregister(Observer o);
    void notifyObservers(String symbol, double price);
}
