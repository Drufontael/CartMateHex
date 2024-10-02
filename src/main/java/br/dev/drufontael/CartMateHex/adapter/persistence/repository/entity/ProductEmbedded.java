package br.dev.drufontael.CartMateHex.adapter.persistence.repository.entity;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class ProductEmbedded {

    private UUID id;
    private String name;
    private int quantity;
    private double price;
    private double total;
    private boolean active;


    public ProductEmbedded() {
        this.total=this.price*this.quantity;
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
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
