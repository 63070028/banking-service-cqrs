package com.example.bankingservicecqrs.util;

public enum Status {
    CREATED("CREATED"),
    ACTIVATED("ACTIVATED"),
    HOLD("HOLD");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}