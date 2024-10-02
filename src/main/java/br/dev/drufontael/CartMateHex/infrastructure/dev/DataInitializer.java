package br.dev.drufontael.CartMateHex.infrastructure.dev;

import br.dev.drufontael.CartMateHex.domain.model.Product;
import br.dev.drufontael.CartMateHex.domain.model.Role;
import br.dev.drufontael.CartMateHex.domain.model.ShoppingList;
import br.dev.drufontael.CartMateHex.domain.model.User;
import br.dev.drufontael.CartMateHex.domain.port.out.ShoppingListPersistencePort;
import br.dev.drufontael.CartMateHex.domain.port.out.UserPersistencePort;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserPersistencePort userPersistencePort;
    private final ShoppingListPersistencePort shoppingListPersistencePort;
    private final PasswordEncoder encoder;


    public DataInitializer(PasswordEncoder encoder, UserPersistencePort userPersistencePort, ShoppingListPersistencePort shoppingListPersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.shoppingListPersistencePort = shoppingListPersistencePort;
        this.encoder = encoder;
    }


    @Override
    public void run(String... args) throws Exception {

        User user = new User(null,"Edu","edu@mail.com","123", Role.USER);
        userPersistencePort.save(user);
        user=userPersistencePort.findByUsername("Edu");

        ShoppingList list=new ShoppingList(null,"Edu", LocalDate.now());
        list.setUser(user);
        list=shoppingListPersistencePort.save(list);

        Product p1 = new Product( "p1",2, 1000.0,true);
        Product p2 = new Product("p2",7, 10.0,true);
        Product p3 = new Product("p3",5, 50.0,true);
        Product p4 = new Product("p4",20, 3.25,true);




        list.setProducts(List.of(p1,p2,p3,p4));

        shoppingListPersistencePort.update(list.getId(),list);


    }
}
