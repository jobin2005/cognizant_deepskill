package com.example.di;

public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public void printCustomer(String id) {
        System.out.println(repository.findCustomerById(id));
    }
}
