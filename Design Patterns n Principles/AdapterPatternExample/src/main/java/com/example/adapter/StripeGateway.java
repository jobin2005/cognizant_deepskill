package com.example.adapter;

// Adaptee with different API
public class StripeGateway {
    public void makePaymentInCents(int cents) {
        System.out.println("Stripe: charging " + cents + " cents");
    }
}
