package br.dev.drufontael.CartMateHex.adapter.web.dto;

import br.dev.drufontael.CartMateHex.domain.model.ShoppingList;

import java.time.LocalDate;

public record ShoppingListInfoResponse(Long id, String name, LocalDate creationDate) {
    public static ShoppingListInfoResponse from(ShoppingList list) {
        return new ShoppingListInfoResponse(list.getId(), list.getName(), list.getCreationDate());
    }
}
