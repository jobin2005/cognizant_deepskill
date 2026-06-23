package com.example.inventory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Map<String, Product> products = new HashMap<>();

    // Add a product; returns false if productId already exists
    public boolean addProduct(Product p) {
        if (p == null || p.getProductId() == null) return false;
        if (products.containsKey(p.getProductId())) return false;
        products.put(p.getProductId(), p);
        return true;
    }

    // Update product fields by id; returns false if not found
    public boolean updateProduct(String productId, String name, Integer quantity, Double price) {
        Product p = products.get(productId);
        if (p == null) return false;
        if (name != null) p.setProductName(name);
        if (quantity != null) p.setQuantity(quantity);
        if (price != null) p.setPrice(price);
        return true;
    }

    // Remove product by id
    public boolean deleteProduct(String productId) {
        return products.remove(productId) != null;
    }

    public Product getProduct(String productId) {
        return products.get(productId);
    }

    public Collection<Product> listProducts() {
        return products.values();
    }
}
