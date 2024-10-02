package br.dev.drufontael.CartMateHex.domain.exception;

public class InvalidShoppingListException extends RuntimeException{

    public InvalidShoppingListException(String message) {
        super(message);
    }
}
