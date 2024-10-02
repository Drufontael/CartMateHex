package br.dev.drufontael.CartMateHex.adapter.persistence.repository.entity;
import br.dev.drufontael.CartMateHex.domain.model.Role;

import br.dev.drufontael.CartMateHex.domain.model.User;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String email;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", nullable = false)
    private Set<Role> roles = new HashSet<>();

    // Relacionamento com ShoppingListEntity
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ShoppingListEntity> shoppingLists = new HashSet<>();

    // Construtores, getters e setters

    public UserEntity() {
    }

    public UserEntity(String username, String password, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<ShoppingListEntity> getShoppingLists() {
        return shoppingLists;
    }

    public void setShoppingLists(Set<ShoppingListEntity> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }

    public User toUser() {
        User user = new User(id,username, password, email);
        user.setRoles(roles);
        return user;
    }
}


