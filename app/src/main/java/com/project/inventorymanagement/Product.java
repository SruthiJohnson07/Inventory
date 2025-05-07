package com.project.inventorymanagement;

public class Product {
    public String id;
    public String name;
    public String quantity;

    public Product() {
        // Required empty constructor
    }

    public Product(String id, String name, String quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }
}
