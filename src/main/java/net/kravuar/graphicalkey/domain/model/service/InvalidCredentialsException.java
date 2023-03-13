package net.kravuar.graphicalkey.domain.model.service;

import lombok.Getter;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class InvalidCredentialsException extends RestException {
    @Getter
    private final String username;
    public InvalidCredentialsException(String username, int attempts) {
        super(attempts);
        this.username = username;
    }

    @Override
    public List<Pair<String, Object[]>> getMessages() {
        var messages = new ArrayList<>(super.getMessages());
        messages.add(0, Pair.of("exception.credentials", new Object[]{username}));
        return messages;
    }

    @Override
    public HttpStatus getCode() {
        return HttpStatus.FORBIDDEN;
    }
}
