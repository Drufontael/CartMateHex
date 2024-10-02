package br.dev.drufontael.CartMateHex.adapter.persistence;

import br.dev.drufontael.CartMateHex.adapter.persistence.repository.entity.ProductEmbedded;
import br.dev.drufontael.CartMateHex.domain.model.Product;
import br.dev.drufontael.CartMateHex.domain.model.ShoppingList;
import br.dev.drufontael.CartMateHex.domain.port.out.ShoppingListPersistencePort;
import br.dev.drufontael.CartMateHex.adapter.persistence.repository.ShoppingListRepository;
import br.dev.drufontael.CartMateHex.adapter.persistence.repository.UserRepository;
import br.dev.drufontael.CartMateHex.adapter.persistence.repository.entity.ShoppingListEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingListPersistenceAdapter implements ShoppingListPersistencePort {

    private final ShoppingListRepository repository;
    private final UserRepository userRepository;


    public ShoppingListPersistenceAdapter(ShoppingListRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }




    @Override
    public ShoppingList save(ShoppingList list) {
        ShoppingListEntity entity = new ShoppingListEntity();
        entity.setName(list.getName());
        entity.setCreationDate(list.getCreationDate());
        entity.setUser(userRepository.findById(list.getUser().getId()).orElseThrow(() -> new RuntimeException("User not found")));
        entity.setProducts(list.getProducts().stream().map(this::mapToEmbedded).collect(Collectors.toSet()));
        return mapToDomain(repository.save(entity));
    }



    @Override
    public ShoppingList findById(Long id) {
        return repository.findById(id).map(this::mapToDomain).orElseThrow(() -> new RuntimeException("List not found"));
    }

    @Override
    public void deleteById(Long id) {
        repository.findById(id).ifPresent(repository::delete);
    }

    @Override
    public ShoppingList update(Long id, ShoppingList list) {
        return repository.findById(id).map(entity -> {
            entity.setName(list.getName());
            entity.setCreationDate(list.getCreationDate());
            entity.setUser(userRepository.findById(list.getUser().getId()).orElseThrow(() -> new RuntimeException("User not found")));
            entity.setProducts(list.getProducts().stream().map(this::mapToEmbedded).collect(Collectors.toSet()));
            return mapToDomain(repository.save(entity));
        }).orElseThrow(() -> new RuntimeException("List not found"));
    }

    @Override
    public List<ShoppingList> getAll(Long userId) {
        return repository.findByUserId(userId).map(ShoppingListEntity->{
            return ShoppingListEntity.stream().map(this::mapToDomain).toList();
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }



    private ShoppingList mapToDomain(ShoppingListEntity entity) {
        ShoppingList list = new ShoppingList();
        list.setId(entity.getId());
        list.setName(entity.getName());
        list.setCreationDate(entity.getCreationDate());
        list.setUser(entity.getUser().toUser());
        list.setProducts(entity.getProducts().stream().map(this::mapToDomain).toList());
        return list;
    }

    Product mapToDomain(ProductEmbedded embedded) {
        Product product = new Product();
        product.setId(embedded.getId());
        product.setName(embedded.getName());
        product.setQuantity(embedded.getQuantity());
        product.setPrice(embedded.getPrice());
        product.setActive(embedded.isActive());
        return product;
    }

    ProductEmbedded mapToEmbedded(Product product){
        ProductEmbedded embedded = new ProductEmbedded();
        embedded.setId(product.getId());
        embedded.setName(product.getName());
        embedded.setQuantity(product.getQuantity());
        embedded.setPrice(product.getPrice());
        embedded.setActive(product.isActive());
        return embedded;
    }


}
