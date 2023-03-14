package net.kravuar.graphicalkey.domain.model;

import lombok.Getter;
import net.kravuar.graphicalkey.props.AuthProps;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Component
@SessionScope
@Getter
public class FailureHandler implements Serializable {
    private int attempts;

    public FailureHandler(AuthProps authProps) {
        this.attempts = authProps.loginAttempts();
    }
    public void dec() {
        this.attempts--;
    }
}