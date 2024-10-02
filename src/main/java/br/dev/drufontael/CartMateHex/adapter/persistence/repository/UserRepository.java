package br.dev.drufontael.CartMateHex.adapter.persistence.repository;

import br.dev.drufontael.CartMateHex.adapter.persistence.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
