package com.example.di;

public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public String findCustomerById(String id) {
        // Simulated lookup
        return "Customer{" + "id='" + id + "', name='John Doe'" + "}";
    }
}
