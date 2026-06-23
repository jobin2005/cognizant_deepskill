package com.example.strategy;

public class TestStrategy {
    public static void main(String[] args) {
        PaymentContext ctx = new PaymentContext();

        ctx.setStrategy(new CreditCardPayment("4111-1111-1111-1111"));
        ctx.executePay(59.99);

        ctx.setStrategy(new PayPalPayment("user@example.com"));
        ctx.executePay(15.00);
    }
}
