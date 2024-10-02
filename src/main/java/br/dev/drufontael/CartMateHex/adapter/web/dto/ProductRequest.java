package br.dev.drufontael.CartMateHex.adapter.web.dto;

import br.dev.drufontael.CartMateHex.domain.model.Product;
import jakarta.annotation.Nonnull;

public record ProductRequest(@Nonnull String name, int quantity, double price, boolean active) {
    public Product mapToDomain() {
        return new Product( name, quantity, price, active);
    }


    public static ProductRequest from(Product product) {
        return new ProductRequest(product.getName(), product.getQuantity(), product.getPrice(), product.isActive());
    }
}
