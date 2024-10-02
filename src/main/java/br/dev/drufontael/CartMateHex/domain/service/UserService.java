package br.dev.drufontael.CartMateHex.domain.service;

import br.dev.drufontael.CartMateHex.domain.model.Role;
import br.dev.drufontael.CartMateHex.domain.model.User;
import br.dev.drufontael.CartMateHex.domain.port.in.ManagerUserPort;
import br.dev.drufontael.CartMateHex.domain.port.out.UserPersistencePort;

public class UserService implements ManagerUserPort {

    private final UserPersistencePort userPersistencePort;

    public UserService(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void createUser(String username, String password, String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.getRoles().add(Role.USER);
        userPersistencePort.save(user);
    }

    @Override
    public void createAdmin(String username, String password, String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.getRoles().add(Role.ADMIN);
        userPersistencePort.save(user);

    }

    @Override
    public User findByUsername(String username) {
        return userPersistencePort.findByUsername(username);
    }
}
