package br.dev.drufontael.CartMateHex.domain.model;

import java.util.HashSet;
import java.util.Set;

public class User {

    private Long id;
    private String username;
    private String password;
    private String email;
    private Set<Role> roles=new HashSet<>();


    public User() {}

    public User(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(Long id, String name, String email, String password, Role role) {
        this.id = id;
        this.username = name;
        this.email = email;
        this.password = password;
        this.roles.add(role);
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
}
