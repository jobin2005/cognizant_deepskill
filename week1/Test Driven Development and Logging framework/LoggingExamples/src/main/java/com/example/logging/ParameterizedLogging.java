package com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParameterizedLogging {
    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLogging.class);

    public static void main(String[] args) {
        String user = "alice";
        String ip = "192.168.1.10";

        logger.info("User {} logged in from {}", user, ip);
        int items = 5;
        double price = 19.99;
        logger.info("Order summary: items={}, total=${}", items, price);
    }
}
