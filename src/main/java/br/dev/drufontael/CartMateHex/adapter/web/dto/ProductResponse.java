package br.dev.drufontael.CartMateHex.adapter.web.dto;

import br.dev.drufontael.CartMateHex.domain.model.Product;

import java.util.UUID;

public record ProductResponse(UUID id,String name, int quantity, double price, double total, boolean active) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getQuantity(),
                product.getPrice(),
                product.getTotal(),
                product.isActive());
    }

    public Product mapToDomain() {
        return new Product( name, quantity, price, active);
    }
}
