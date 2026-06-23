package com.example.adapter;

public class TestAdapter {
    public static void main(String[] args) {
        PaymentProcessor stripe = new StripeAdapter();
        PaymentProcessor paypal = new PayPalAdapter();

        stripe.processPayment(12.34);
        paypal.processPayment(9.99);
    }
}
