package com.example.adapter;

public class PayPalAdapter implements PaymentProcessor {
    private final PayPalGateway payPal = new PayPalGateway();

    @Override
    public void processPayment(double amount) {
        payPal.sendPayment("USD", amount);
    }
}
