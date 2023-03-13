package net.kravuar.graphicalkey;

import com.mongodb.DuplicateKeyException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import net.kravuar.graphicalkey.domain.dto.LoginMessage;
import net.kravuar.graphicalkey.domain.model.service.RestException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RestController
@RequiredArgsConstructor
public class ExceptionAdvice {
    private final MessageSource ms;
    private final LocalizationProps i18nProps;

    @ExceptionHandler(RestException.class)
    public ResponseEntity<LoginMessage> handleRestException(RestException exception) {
        var locale = i18nProps.supportedLocales().contains(LocaleContextHolder.getLocale().toLanguageTag())
                ? LocaleContextHolder.getLocale()
                : Locale.forLanguageTag(i18nProps.defaultLocale());
        var localized = exception.getMessages().stream()
                .map(message -> ms.getMessage(
                        message.getFirst(),
                        message.getSecond(),
                        locale))
                .toList();

        return new ResponseEntity<>(new LoginMessage(localized, exception.getAttemptsLeft()), exception.getCode());
    }
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateKeyException.class)
    public String handleValidationError(DuplicateKeyException exception) {
        return exception.getMessage();
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(BindException.class)
    public Set<String> handleValidationError(BindException exception) {
        return exception.getBindingResult()
                .getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toSet());
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ConstraintViolationException.class)
    public Set<String> handleValidationError(ConstraintViolationException exception) {
        return exception.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toSet());
    }
}