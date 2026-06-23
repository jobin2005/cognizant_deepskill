package com.example.employee;

public class TestEmployee {
    public static void main(String[] args) {
        EmployeeArrayManager mgr = new EmployeeArrayManager(5);

        mgr.addEmployee(new Employee("E001","Alice","Engineer",70000));
        mgr.addEmployee(new Employee("E002","Bob","Manager",90000));
        mgr.addEmployee(new Employee("E003","Carol","Analyst",60000));

        System.out.println("Traverse employees:");
        mgr.traverse();

        System.out.println("Search E002: " + mgr.searchById("E002"));

        System.out.println("Delete E001: " + mgr.deleteById("E001"));
        System.out.println("After delete traverse:");
        mgr.traverse();

        System.out.println("Current size: " + mgr.getSize());
    }
}
