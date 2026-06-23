package com.example.observer;

public class TestObserver {
    public static void main(String[] args) {
        StockMarket market = new StockMarket();
        MobileApp mobile = new MobileApp("TraderMobile");
        WebApp web = new WebApp("TraderWeb");

        market.register(mobile);
        market.register(web);

        market.setPrice("AAPL", 174.5);
        market.setPrice("GOOG", 2830.0);
    }
}
