package br.dev.drufontael.CartMateHex.infrastructure.configuration.documentation.api;

import br.dev.drufontael.CartMateHex.adapter.web.dto.ShoppingListInfoResponse;
import br.dev.drufontael.CartMateHex.adapter.web.dto.UserRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface AuthUserAPI {

    @Operation(summary = "Register user", tags = {"Auth-Users"})
    ResponseEntity<String> register( UserRequest request);
    @Operation(summary = "Login user", tags = {"Auth-Users"})
    ResponseEntity<List<ShoppingListInfoResponse>> login(UserRequest request, HttpServletRequest httpRequest, HttpServletResponse response);
}
