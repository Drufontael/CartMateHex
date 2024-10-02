package br.dev.drufontael.CartMateHex.adapter.persistence;

import br.dev.drufontael.CartMateHex.domain.model.User;
import br.dev.drufontael.CartMateHex.domain.port.out.UserPersistencePort;
import br.dev.drufontael.CartMateHex.adapter.persistence.repository.UserRepository;
import br.dev.drufontael.CartMateHex.adapter.persistence.repository.entity.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserPersistenceAdapter implements UserPersistencePort {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserPersistenceAdapter(UserRepository repository, PasswordEncoder encoder) {
        this.encoder = encoder;
        this.repository = repository;
    }
    @Override
    public void save(User user) {
        UserEntity entity = new UserEntity();
        entity.setUsername(user.getUsername());
        entity.setPassword(encoder.encode(user.getPassword()));
        entity.setEmail(user.getEmail());
        entity.setRoles(user.getRoles());
        repository.save(entity);

    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username).map(UserEntity -> {
            return UserEntity.toUser();
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id).map(UserEntity::toUser).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
