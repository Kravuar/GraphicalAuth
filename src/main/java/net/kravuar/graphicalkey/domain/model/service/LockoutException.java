package net.kravuar.graphicalkey.domain.model.service;

import lombok.Getter;

@Getter
public class LockoutException extends RuntimeException {
    private static final String ms = "exception.lockout";
    private final int timeout;
    public LockoutException() {
        super(...);
        this.timeout = {From Properties};
    }
}
