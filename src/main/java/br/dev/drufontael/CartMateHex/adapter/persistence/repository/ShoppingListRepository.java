package br.dev.drufontael.CartMateHex.adapter.persistence.repository;

import br.dev.drufontael.CartMateHex.adapter.persistence.repository.entity.ShoppingListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShoppingListRepository extends JpaRepository<ShoppingListEntity, Long> {
    Optional<List<ShoppingListEntity>> findByUserId(Long userId);
}
