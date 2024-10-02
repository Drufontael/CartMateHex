package br.dev.drufontael.CartMateHex.domain.port.out;

import br.dev.drufontael.CartMateHex.domain.model.User;

public interface UserPersistencePort {

    void save(User user);

    User findByUsername(String username);

    User findById(Long id);

}
