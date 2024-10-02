package br.dev.drufontael.CartMateHex.adapter.web;

import br.dev.drufontael.CartMateHex.adapter.web.dto.ShoppingListInfoResponse;
import br.dev.drufontael.CartMateHex.adapter.web.dto.UserRequest;
import br.dev.drufontael.CartMateHex.domain.model.User;
import br.dev.drufontael.CartMateHex.domain.port.in.ManagerUserPort;
import br.dev.drufontael.CartMateHex.domain.port.in.ManageShoppingListPort;
import br.dev.drufontael.CartMateHex.infrastructure.configuration.documentation.api.AuthUserAPI;
import br.dev.drufontael.CartMateHex.infrastructure.configuration.security.CustomUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.Authenticator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class UserAcessController implements AuthUserAPI {

    private final ManagerUserPort manageUserPort;
    private final ManageShoppingListPort manageShoppingListPort;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;

    public UserAcessController(ManagerUserPort createUserPort, ManageShoppingListPort manageShoppingListPort,
                               AuthenticationManager authenticationManager, PasswordEncoder encoder) {
        this.encoder = encoder;
        this.manageUserPort = createUserPort;
        this.manageShoppingListPort = manageShoppingListPort;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequest request) {
        manageUserPort.createUser(request.username(), request.password(), request.email());
        return ResponseEntity.ok("Usuário criado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<List<ShoppingListInfoResponse>> login(@RequestBody UserRequest request, HttpServletRequest httpRequest, HttpServletResponse response) {
        User userAuth = manageUserPort.findByUsername(request.username());
        if (!encoder.matches(request.password(), userAuth.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(httpRequest, response, auth);
        }


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                       request.username(), request.password()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok(manageShoppingListPort.getAllLists(userAuth.getId())
                .stream().map(ShoppingListInfoResponse::from).toList());
    }
}
