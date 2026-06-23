package com.example.di;

public class TestDI {
    public static void main(String[] args) {
        CustomerRepository repo = new CustomerRepositoryImpl();
        CustomerService service = new CustomerService(repo);

        service.printCustomer("C001");
    }
}
