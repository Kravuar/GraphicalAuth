package net.kravuar.graphicalkey.domain.model;

import lombok.Getter;
import net.kravuar.graphicalkey.AuthProps;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Getter
public class FailureHandler {
    private int attempts;

    public FailureHandler(AuthProps authProps) {
        this.attempts = authProps.maxAttempts();
    }
    public void dec() {
        this.attempts++;
    }
}