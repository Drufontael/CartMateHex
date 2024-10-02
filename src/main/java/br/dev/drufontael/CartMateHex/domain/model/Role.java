package br.dev.drufontael.CartMateHex.domain.model;

public enum Role {
    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    public final String role;

    Role(String role) {
        this.role = role;
    }
}
