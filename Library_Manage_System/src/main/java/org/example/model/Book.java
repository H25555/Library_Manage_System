package org.example.model;

import java.time.LocalDate;
import java.util.Date;

public class Book {
    private int id;
    private String name;
    private Category category;
    private Publisher publisher;
    private Status status;
    private LocalDate importdate;
    private int quantity;

    public Book(int id, String name, Category category, Publisher publisher, Status status, LocalDate importdate, int quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.publisher = publisher;
        this.status = status;
        this.importdate = importdate;
        this.quantity = quantity;
    }

    public LocalDate getImportdate() {
        return importdate;
    }

    public void setImportdate(LocalDate importdate) {
        this.importdate = importdate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Book(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.getId() + ";" + this.getName() + ";" + this.getCategory() + ";" + this.getPublisher()+ ";"+ this.getStatus() + ";"+ this.getImportdate() + ";" +this.getQuantity() ;
    }
}
