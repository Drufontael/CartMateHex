package br.dev.drufontael.CartMateHex.domain.port.out;

import br.dev.drufontael.CartMateHex.domain.model.ShoppingList;
import java.util.List;

public interface ShoppingListPersistencePort {

    public ShoppingList save(ShoppingList list);
    public ShoppingList findById(Long id);
    public void deleteById(Long id);
    public ShoppingList update(Long id, ShoppingList list);

    public List<ShoppingList> getAll(Long userId);

}
