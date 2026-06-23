package com.example.junit;

public class MyService {
    private final ExternalApi api;

    public MyService(ExternalApi api) {
        this.api = api;
    }

    public String fetchData() {
        return api.getData();
    }

    public String fetchDataWithArg(String arg) {
        return api.getDataWithArg(arg);
    }

    public void doVoid() {
        api.voidMethod();
    }

    public String consecutive() {
        return api.getNext() + "," + api.getNext();
    }

    public void callInOrder() {
        api.first();
        api.second();
    }

    public void voidThrows() {
        api.voidMethod();
    }
}
