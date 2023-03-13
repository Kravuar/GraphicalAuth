package net.kravuar.graphicalkey.domain.model.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;

import java.util.List;

@RequiredArgsConstructor
public class RestException extends RuntimeException {
    @Getter
    private final int attemptsLeft;

    public List<Pair<String, Object[]>> getMessages() {
        return List.of(
                Pair.of("exception.loginFailure", new Object[]{attemptsLeft})
        );
    }

    public HttpStatus getCode() {
        return HttpStatus.FORBIDDEN;
    }
}