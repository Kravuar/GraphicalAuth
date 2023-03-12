package net.kravuar.graphicalkey.domain.model.service;

public class UserNotFoundException extends LoginFailureException {
    private static final String ms = "exception.userNotFound";
    public UserNotFoundException(String username, int attempts) {
        super(..., attempts);
    }
}
