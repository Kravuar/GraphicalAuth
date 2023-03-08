package net.kravuar.graphicalkey.domain.model;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super(username + " not found");
    }
}
