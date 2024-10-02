package br.dev.drufontael.CartMateHex.domain.port.in;

import br.dev.drufontael.CartMateHex.domain.model.Product;
import br.dev.drufontael.CartMateHex.domain.model.ShoppingList;

import java.util.List;
import java.util.UUID;

public interface ManageShoppingListPort {

    ShoppingList createList(String name, Long userId);
    ShoppingList updateList(Long id, ShoppingList list);
    void deleteList(Long id);
    ShoppingList getListById(Long id, Long userId);
    void addProduct(Long listId, Product product);
    void removeProduct(Long listId, UUID productId);
    void updateProduct(Long listId, UUID productId, Product product);

    void updateProduct(Long listId, UUID productId);
    List<ShoppingList> getAllLists(Long userId);
}
