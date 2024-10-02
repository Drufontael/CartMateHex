package br.dev.drufontael.CartMateHex.infrastructure.configuration.documentation.api;

import br.dev.drufontael.CartMateHex.adapter.web.dto.ProductRequest;
import br.dev.drufontael.CartMateHex.infrastructure.configuration.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;

import java.util.UUID;


public interface ProductAPI {

    @Operation(summary = "Add product", tags = {"Products"})
    ResponseEntity<Void> addProduct(Long id,ProductRequest request,CustomUserDetails user);
    @Operation(summary = "Update product", tags = {"Products"})
    ResponseEntity<Void> updateProduct( Long id,UUID productId,ProductRequest request, CustomUserDetails user);
    @Operation(summary = "Change status between active/inactive", tags = {"Products"})
    ResponseEntity<Void> updateProduct(Long id,UUID productId,CustomUserDetails user);
    @Operation(summary = "Remove product", tags = {"Products"})
    ResponseEntity<Void> removeProduct(Long id,UUID productId,CustomUserDetails user);
}
