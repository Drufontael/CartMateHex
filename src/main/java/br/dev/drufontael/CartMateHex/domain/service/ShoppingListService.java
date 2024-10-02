package br.dev.drufontael.CartMateHex.domain.service;

import br.dev.drufontael.CartMateHex.domain.exception.InvalidShoppingListException;
import br.dev.drufontael.CartMateHex.domain.exception.InvalidUserException;
import br.dev.drufontael.CartMateHex.domain.model.Product;
import br.dev.drufontael.CartMateHex.domain.model.ShoppingList;
import br.dev.drufontael.CartMateHex.domain.model.User;
import br.dev.drufontael.CartMateHex.domain.port.in.ManageShoppingListPort;
import br.dev.drufontael.CartMateHex.domain.port.out.ShoppingListPersistencePort;
import br.dev.drufontael.CartMateHex.domain.port.out.UserPersistencePort;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class ShoppingListService implements ManageShoppingListPort {
    private final ShoppingListPersistencePort persistencePort;
    private final UserPersistencePort userPersistencePort;

    public ShoppingListService(ShoppingListPersistencePort persistencePort, UserPersistencePort userPersistencePort) {
        this.persistencePort = persistencePort;
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public ShoppingList createList(String name, Long userId) {
        if (name == null || name.isEmpty()) {
            throw new InvalidUserException("Name cannot be null or empty");
        }
        User user = userPersistencePort.findById(userId);
        ShoppingList list = new ShoppingList();
        list.setName(name);
        list.setCreationDate(LocalDate.now());
        list.setUser(user);
        return persistencePort.save(list);

    }

    @Override
    public ShoppingList updateList(Long id, ShoppingList list) {
        if (list == null) {
            throw new InvalidShoppingListException("List cannot be null");
        }
        return persistencePort.update(id, list);
    }

    @Override
    public void deleteList(Long id) {
        persistencePort.deleteById(id);
    }

    @Override
    public ShoppingList getListById(Long id, Long userId) {
        System.out.println("Id: " + id + " userId: " + userId);
        User user = userPersistencePort.findById(userId);
        if (user == null) {
            throw new InvalidUserException("User not found");
        }
        ShoppingList list = persistencePort.findById(id);
        if (list == null) {
            throw new InvalidShoppingListException("List not found");
        }
        if (list.getUser().getId() != user.getId()) {
            throw new InvalidShoppingListException("List does not belong to user");
        }
        return list;
    }

    @Override
    public void addProduct(Long listId, Product product) {
        ShoppingList list = persistencePort.findById(listId);
        if (list == null) {
            throw new InvalidShoppingListException("List not found");
        }
        list.getProducts().add(product);
        persistencePort.update(listId, list);
    }

    @Override
    public void removeProduct(Long listId, UUID productId) {
        ShoppingList list = persistencePort.findById(listId);
        if (list == null) {
            throw new InvalidShoppingListException("List not found");
        }
        list.getProducts().removeIf(product -> product.getId().equals(productId));
        persistencePort.update(listId, list);
    }

    @Override
    public void updateProduct(Long listId, UUID productId, Product product) {
        ShoppingList list = persistencePort.findById(listId);
        if (list == null) {
            throw new InvalidShoppingListException("List not found");
        }
        list.getProducts().removeIf(p -> p.getId().equals(productId));
        list.getProducts().add(product);
        persistencePort.update(listId, list);
    }

    public void updateProduct(Long listId, UUID productId) {
        ShoppingList list = persistencePort.findById(listId);
        if (list == null) {
            throw new InvalidShoppingListException("List not found");
        }
        list.getProducts().stream().filter(p->p.getId().equals(productId)).forEach(p -> p.setActive(!p.isActive()));
        persistencePort.update(listId, list);
    }


    @Override
    public List<ShoppingList> getAllLists(Long userId) {
        return persistencePort.getAll(userId);
    }
}
