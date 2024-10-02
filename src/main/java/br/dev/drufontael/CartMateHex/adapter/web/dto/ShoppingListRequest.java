package br.dev.drufontael.CartMateHex.adapter.web.dto;

import br.dev.drufontael.CartMateHex.domain.model.ShoppingList;

import java.time.LocalDate;
import java.util.List;

public record ShoppingListRequest(Long id, String name, LocalDate creationDate, List<ProductRequest> products) {

    public static ShoppingListRequest from(ShoppingList list) {
        List<ProductRequest> products = list.getProducts().stream().map(ProductRequest::from).toList();
        return new ShoppingListRequest(list.getId(), list.getName(), list.getCreationDate(), products);
    }

    public ShoppingList mapToDomain() {
        ShoppingList list = new ShoppingList();
        list.setId(this.id);
        list.setName(this.name());
        list.setCreationDate(this.creationDate());
        list.setProducts(this.products().stream().map(ProductRequest::mapToDomain).toList());
        return list;

    }
}