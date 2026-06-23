package com.example.adapter;

public class StripeAdapter implements PaymentProcessor {
    private final StripeGateway stripe = new StripeGateway();

    @Override
    public void processPayment(double amount) {
        int cents = (int) Math.round(amount * 100);
        stripe.makePaymentInCents(cents);
    }
}
