package br.dev.drufontael.CartMateHex.adapter.web.dto;

import br.dev.drufontael.CartMateHex.domain.model.ShoppingList;
import jakarta.annotation.Nonnull;

public record ShoppingListCreateRequest(@Nonnull String name) {


}
