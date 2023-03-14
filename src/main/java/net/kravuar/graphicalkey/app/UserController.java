package net.kravuar.graphicalkey.app;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kravuar.graphicalkey.domain.dto.UserForm;
import net.kravuar.graphicalkey.domain.model.FailureHandler;
import net.kravuar.graphicalkey.domain.model.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
@Validated
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {
    private final AuthService authService;
    private final FailureHandler handler;

    @PostMapping("signup")
    public User signup(@RequestBody @Valid UserForm userForm) {
        var user = authService.signup(userForm);
        log.info("SIGNUP: {}", user);
        return user;
    }

    @PostMapping("login")
    public String login(@RequestBody @Valid UserForm userForm) {
        log.info("LOGIN ATTEMPT: {}", userForm.username());
        return authService.login(userForm, handler);
    }
}