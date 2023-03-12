package net.kravuar.graphicalkey.domain.dto;

import net.kravuar.graphicalkey.domain.model.service.LoginFailureException;

public record LoginFailureDTO(String message, int attemptsLeft) {
    public LoginFailureDTO(LoginFailureException exception) {
        this(exception.getMessage(), exception.getAttemptsLeft());
    }
}
