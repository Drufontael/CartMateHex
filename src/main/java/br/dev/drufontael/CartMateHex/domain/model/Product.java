package br.dev.drufontael.CartMateHex.domain.model;

import java.util.UUID;

public class Product {

    private UUID id;
    private String name;
    private int quantity;
    private double price;
    private double total;
    private boolean active;


    public Product() {
    }

    public Product(String name, int quantity, double price, boolean active) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.total = quantity * price;
        this.active = active;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        setTotal();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {

        this.price = price;
        setTotal();
    }

    public double getTotal() {
        return total;
    }

    private void setTotal() {
        this.total = this.quantity * this.price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }



}
