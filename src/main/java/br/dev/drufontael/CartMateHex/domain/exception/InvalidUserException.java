package br.dev.drufontael.CartMateHex.domain.exception;

public class InvalidUserException extends RuntimeException{

    public InvalidUserException(String message) {
        super(message);
    }
}
