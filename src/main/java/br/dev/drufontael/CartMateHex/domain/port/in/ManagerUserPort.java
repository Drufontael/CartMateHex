package br.dev.drufontael.CartMateHex.domain.port.in;

import br.dev.drufontael.CartMateHex.domain.model.User;

public interface ManagerUserPort {

    void createUser(String username, String password, String email);

    void createAdmin(String username, String password, String email);

    User findByUsername(String username);
}
