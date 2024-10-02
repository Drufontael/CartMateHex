package br.dev.drufontael.CartMateHex.infrastructure.configuration.documentation.api;

import br.dev.drufontael.CartMateHex.adapter.web.dto.ShoppingListCreateRequest;
import br.dev.drufontael.CartMateHex.adapter.web.dto.ShoppingListInfoResponse;
import br.dev.drufontael.CartMateHex.adapter.web.dto.ShoppingListRequest;
import br.dev.drufontael.CartMateHex.adapter.web.dto.ShoppingListResponse;
import br.dev.drufontael.CartMateHex.infrastructure.configuration.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ShoppingListAPI {

    @Operation(summary = "Create list", tags = {"Shopping Lists"})
    ResponseEntity<ShoppingListInfoResponse> createList(ShoppingListCreateRequest request, CustomUserDetails user);
    @Operation(summary = "Get lists", tags = {"Shopping Lists"})
    ResponseEntity<List<ShoppingListInfoResponse>> getAllLists(CustomUserDetails user);
    @Operation(summary = "Get list by id", tags = {"Shopping Lists"})
    ResponseEntity<ShoppingListResponse> getList(Long id,CustomUserDetails user);
    @Operation(summary = "Update list", tags = {"Shopping Lists"})
    ResponseEntity<ShoppingListResponse> updateList(Long id, ShoppingListRequest request, CustomUserDetails user);
    @Operation(summary = "Delete list", tags = {"Shopping Lists"})
    ResponseEntity<Void> deleteList(Long id, CustomUserDetails user);

}
