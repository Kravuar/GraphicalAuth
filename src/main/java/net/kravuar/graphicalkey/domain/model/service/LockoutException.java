package net.kravuar.graphicalkey.domain.model.service;

import lombok.Getter;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
public class LockoutException extends RestException {
    private final int timeout;
    public LockoutException(int timeout) {
        super(0);
        this.timeout = timeout;
    }

    @Override
    public List<Pair<String, Object[]>> getMessages() {
        var messages = new ArrayList<>(super.getMessages());
        messages.add(0, Pair.of("exception.lockout", new Object[]{timeout}));
        return messages;
    }

    @Override
    public HttpStatus getCode() {
        return HttpStatus.LOCKED;
    }
}