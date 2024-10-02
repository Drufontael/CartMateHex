package br.dev.drufontael.CartMateHex.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShoppingList {

    private Long id;
    private String name;
    private LocalDate creationDate;
    private User user;
    private List<Product> products =new ArrayList<>();
    private double total;
    private double totalActiveProducts;

    public ShoppingList() {}

    public ShoppingList(Long id, String name, LocalDate creationDate) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.total = products.stream().filter(Product::isActive).mapToDouble(Product::getTotal).sum();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        this.total = products.stream().filter(Product::isActive).mapToDouble(Product::getTotal).sum();
    }

    public double getTotal() {
        return products.stream().mapToDouble(Product::getTotal).sum();
    }

    public double getTotalActiveProducts() {
        return products.stream().filter(Product::isActive).mapToDouble(Product::getTotal).sum();
    }


}
