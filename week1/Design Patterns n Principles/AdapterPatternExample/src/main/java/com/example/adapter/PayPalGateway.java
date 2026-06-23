package com.example.adapter;

// Another adaptee with different method signature
public class PayPalGateway {
    public void sendPayment(String currency, double amount) {
        System.out.println("PayPal: sending " + amount + " " + currency);
    }
}
