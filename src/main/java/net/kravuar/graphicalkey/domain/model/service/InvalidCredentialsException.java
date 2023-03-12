package net.kravuar.graphicalkey.domain.model.service;

public class InvalidCredentialsException extends LoginFailureException {
    private static final String ms = "exception.credentials";
    public InvalidCredentialsException(String username, int attempts) {
        super(..., attempts);
    }
}
