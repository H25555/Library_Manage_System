package org.example.model;

public enum Status {
    INSTOCK("instock"), OUTOFSTOCK("out of stock");

    private final String value;

    Status(String value) {
        this.value = value;
    }
}
