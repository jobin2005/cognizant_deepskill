package com.example.strategy;

public class PaymentContext {
    private PaymentStrategy strategy;

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void executePay(double amount) {
        if (strategy == null) {
            throw new IllegalStateException("PaymentStrategy not set");
        }
        strategy.pay(amount);
    }
}
