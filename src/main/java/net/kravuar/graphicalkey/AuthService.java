package net.kravuar.graphicalkey;

import net.kravuar.graphicalkey.domain.dto.UserForm;
import net.kravuar.graphicalkey.domain.model.*;
import net.kravuar.graphicalkey.domain.model.FailureHandler;
import net.kravuar.graphicalkey.domain.model.service.InvalidCredentialsException;
import net.kravuar.graphicalkey.domain.model.service.LockoutException;
import net.kravuar.graphicalkey.domain.model.service.UserNotFoundException;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class AuthService {
    private final UserRepo userRepo;
    private final MessageDigest md;

    public AuthService(UserRepo userRepo) throws NoSuchAlgorithmException {
        this.userRepo = userRepo;
        this.md = MessageDigest.getInstance("SHA-1");
    }

    public User signup(UserForm userForm) {
        return userRepo.insert(new User(userForm.username(), toBinary(userForm.key())));
    }
    public String login(UserForm userForm, FailureHandler handler) {
        if (handler.getAttempts() <= 0)
            throw new LockoutException();

        var user = userRepo.findByUsername(userForm.username());
        if (user == null) {
            handler.dec();
            throw new UserNotFoundException(userForm.username(), handler.getAttempts());
        }

        if (user.getKeyHash().equals(toBinary(userForm.key()))) {
            handler.dec();
            throw new InvalidCredentialsException(userForm.username(), handler.getAttempts());
        }

        return getTokenFor(user);
    }

    private Binary toBinary(short[] key) {
        var bb = ByteBuffer.allocate(key.length * 2);
        for(short value: key)
            bb.putShort(value);

        return new Binary(md.digest(bb.array()));
    }
    private String getTokenFor(User user) {
        return "He-he, no token, he-he";
    }
}