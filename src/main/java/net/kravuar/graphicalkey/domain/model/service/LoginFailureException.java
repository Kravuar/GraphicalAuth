package net.kravuar.graphicalkey.domain.model.service;

import lombok.Getter;

@Getter
public class LoginFailureException extends RuntimeException {
    private static final String ms = "exception.loginFailure";
    private final int attemptsLeft;
    public LoginFailureException(String message, int attemptsLeft) {
        super(...);
        this.attemptsLeft = attemptsLeft;
    }
}
