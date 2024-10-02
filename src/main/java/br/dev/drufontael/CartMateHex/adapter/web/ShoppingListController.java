package br.dev.drufontael.CartMateHex.adapter.web;

import br.dev.drufontael.CartMateHex.adapter.web.dto.*;
import br.dev.drufontael.CartMateHex.domain.exception.InvalidShoppingListException;
import br.dev.drufontael.CartMateHex.domain.model.User;
import br.dev.drufontael.CartMateHex.domain.port.in.ManageShoppingListPort;
import br.dev.drufontael.CartMateHex.infrastructure.configuration.documentation.api.ProductAPI;
import br.dev.drufontael.CartMateHex.infrastructure.configuration.documentation.api.ShoppingListAPI;
import br.dev.drufontael.CartMateHex.infrastructure.configuration.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/shopping-lists")
public class ShoppingListController implements ShoppingListAPI, ProductAPI {

    private final ManageShoppingListPort manageShoppingListPort;

    public ShoppingListController(ManageShoppingListPort manageShoppingListPort) {
        this.manageShoppingListPort = manageShoppingListPort;
    }

    @PostMapping
    public ResponseEntity<ShoppingListInfoResponse> createList(@RequestBody ShoppingListCreateRequest request, @AuthenticationPrincipal CustomUserDetails user) {
        return ResponseEntity.ok()
                .body(ShoppingListInfoResponse.from(manageShoppingListPort.createList(request.name(), user.getUserId())));
    }

    @GetMapping
    public ResponseEntity<List<ShoppingListInfoResponse>> getAllLists(@AuthenticationPrincipal CustomUserDetails user) {
        return ResponseEntity.ok()
                .body(manageShoppingListPort.getAllLists(user.getUserId()).stream().map(ShoppingListInfoResponse::from).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingListResponse> getList(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails user) {
        return ResponseEntity.ok()
                .body(ShoppingListResponse.from(manageShoppingListPort.getListById(id, user.getUserId())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoppingListResponse> updateList(@PathVariable Long id, @RequestBody ShoppingListRequest request, @AuthenticationPrincipal CustomUserDetails user) {
        validateUser(id, user);
        return ResponseEntity.ok()
                .body(ShoppingListResponse.from(manageShoppingListPort.updateList(id, request.mapToDomain())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteList(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails user) {
        validateUser(id, user);
        manageShoppingListPort.deleteList(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/products")
    public ResponseEntity<Void> addProduct(@PathVariable Long id, @RequestBody ProductRequest request, @AuthenticationPrincipal CustomUserDetails user) {
        validateUser(id, user);
        manageShoppingListPort.addProduct(id, request.mapToDomain());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/products/{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @PathVariable UUID productId, @RequestBody ProductRequest request, @AuthenticationPrincipal CustomUserDetails user) {
        validateUser(id, user);
        manageShoppingListPort.updateProduct(id, productId, request.mapToDomain());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/products/{productId}/switch")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @PathVariable UUID productId, @AuthenticationPrincipal CustomUserDetails user) {
        validateUser(id, user);
        manageShoppingListPort.updateProduct(id, productId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/products/{productId}")
    public ResponseEntity<Void> removeProduct(@PathVariable Long id, @PathVariable UUID productId, @AuthenticationPrincipal CustomUserDetails user) {
        validateUser(id, user);
        manageShoppingListPort.removeProduct(id, productId);
        return ResponseEntity.noContent().build();
    }

    private void validateUser(Long listId, CustomUserDetails user) {
        User userAuth = manageShoppingListPort.getListById(listId, user.getUserId()).getUser();
        if (!userAuth.getId().equals(user.getUserId())) {
            throw new InvalidShoppingListException("List does not belong to user");
        }
    }


}
