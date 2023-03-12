package net.kravuar.graphicalkey;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kravuar.graphicalkey.domain.dto.UserForm;
import net.kravuar.graphicalkey.domain.model.FailureHandler;
import net.kravuar.graphicalkey.domain.model.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
@Validated
public class UserController {
    private final AuthService authService;

    @PostMapping("signup")
    public User signup(@Valid UserForm userForm) {
        var user = authService.signup(userForm);
        log.info("SIGNUP: {}", user);
        return user;
    }

    @PostMapping("login")
    public String login(@Valid UserForm userForm, FailureHandler handler) {
        log.info("LOGIN ATTEMPT: {}", userForm.username());
        return authService.login(userForm, handler);
    }
}

