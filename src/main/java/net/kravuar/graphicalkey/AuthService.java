package net.kravuar.graphicalkey;

import net.kravuar.graphicalkey.domain.dto.UserForm;
import net.kravuar.graphicalkey.domain.model.InvalidCredentialsException;
import net.kravuar.graphicalkey.domain.model.User;
import net.kravuar.graphicalkey.domain.model.UserNotFoundException;
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
    public String login(UserForm userForm) {
        var user = userRepo.findByUsername(userForm.username());
        if (user == null)
            throw new UserNotFoundException(userForm.username());

        if (user.getKeyHash().equals(toBinary(userForm.key())))
            throw new InvalidCredentialsException(userForm.username());

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