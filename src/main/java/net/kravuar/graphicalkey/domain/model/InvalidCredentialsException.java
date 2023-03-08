package net.kravuar.graphicalkey.domain.model;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String username) {
        super("Invalid credentials for " + username);
    }
}
