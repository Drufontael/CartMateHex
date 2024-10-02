package br.dev.drufontael.CartMateHex.adapter.web.dto;

import br.dev.drufontael.CartMateHex.domain.model.ShoppingList;

import java.time.LocalDate;
import java.util.List;

public record ShoppingListResponse(Long id,
                                   String name,
                                   LocalDate creationDate,
                                   List<ProductResponse> products,
                                   double total,
                                   double totalActiveProducts) {

    public static ShoppingListResponse from(ShoppingList list) {
        List<ProductResponse> products = list.getProducts().stream().map(ProductResponse::from).toList();
        return new ShoppingListResponse(list.getId(),
                list.getName(),
                list.getCreationDate(),
                products,
                list.getTotal(),
                list.getTotalActiveProducts());
    }
}
