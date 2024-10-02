package br.dev.drufontael.CartMateHex.infrastructure.configuration.beans;

import br.dev.drufontael.CartMateHex.domain.port.in.ManageShoppingListPort;
import br.dev.drufontael.CartMateHex.domain.port.in.ManagerUserPort;
import br.dev.drufontael.CartMateHex.domain.port.out.ShoppingListPersistencePort;
import br.dev.drufontael.CartMateHex.domain.port.out.UserPersistencePort;
import br.dev.drufontael.CartMateHex.domain.service.ShoppingListService;
import br.dev.drufontael.CartMateHex.domain.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfig {

    private final ShoppingListPersistencePort shoppingListPersistencePort;
    private final UserPersistencePort userPersistencePort;

    public ServicesConfig(ShoppingListPersistencePort shoppingListPersistencePort,
                          UserPersistencePort userPersistencePort) {
        this.shoppingListPersistencePort = shoppingListPersistencePort;
        this.userPersistencePort = userPersistencePort;
    }

    @Bean
    public ManageShoppingListPort shoppingListService() {
        return new ShoppingListService(shoppingListPersistencePort, userPersistencePort);
    }

    @Bean
    public ManagerUserPort userService() {
        return new UserService(userPersistencePort);
    }


}

