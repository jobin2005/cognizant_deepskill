package com.example.inventory;

public class TestInventory {
    public static void main(String[] args) {
        Inventory inv = new Inventory();

        Product p1 = new Product("P001", "Widget", 100, 2.5);
        Product p2 = new Product("P002", "Gadget", 50, 5.0);

        System.out.println("Add p1: " + inv.addProduct(p1));
        System.out.println("Add p2: " + inv.addProduct(p2));
        System.out.println("Add p1 again (should be false): " + inv.addProduct(p1));

        System.out.println("List products:");
        inv.listProducts().forEach(System.out::println);

        System.out.println("Update P002 quantity to 75 and price to 4.75: " + inv.updateProduct("P002", null, 75, 4.75));
        System.out.println("Get P002: " + inv.getProduct("P002"));

        System.out.println("Delete P001: " + inv.deleteProduct("P001"));
        System.out.println("Get P001 (should be null): " + inv.getProduct("P001"));
    }
}
