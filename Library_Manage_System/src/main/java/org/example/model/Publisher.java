package org.example.model;

public enum Publisher {
    KIMDONG("Kim Đồng"), PHUONGNAM("Phương Nam"), THEKY("Thế kỷ");

    private final String value;

    Publisher(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
