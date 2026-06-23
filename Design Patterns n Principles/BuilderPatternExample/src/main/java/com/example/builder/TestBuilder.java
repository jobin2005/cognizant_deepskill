package com.example.builder;

public class TestBuilder {
    public static void main(String[] args) {
        Computer gaming = new Computer.Builder()
                .cpu("Intel i9")
                .ramGb(32)
                .storageGb(2000)
                .gpu("NVIDIA RTX 4090")
                .build();

        Computer office = new Computer.Builder()
                .cpu("Intel i5")
                .ramGb(8)
                .storageGb(512)
                .build();

        System.out.println("Gaming: " + gaming);
        System.out.println("Office: " + office);
    }
}
