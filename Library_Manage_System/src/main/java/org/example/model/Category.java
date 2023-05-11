package org.example.model;

public enum Category {
    NOVEL("novel"), SHORT("short"), MEMOIR("memoir"), SCIFI("science fiction"), DETECTIVE("detective");

    private final String value;

    Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
