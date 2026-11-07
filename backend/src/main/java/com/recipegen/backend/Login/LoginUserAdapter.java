package com.recipegen.backend.Login;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@SuppressWarnings("unused")
@Component
public class LoginUserAdapter implements ILoginUser {
    private final JpaLoginUserRepo loginUserRepo;
    private final PasswordEncoder encoder;

    public LoginUserAdapter(JpaLoginUserRepo loginUserRepo,
                            PasswordEncoder encoder) {
        this.loginUserRepo = loginUserRepo;
        this.encoder = encoder;
    }

    @Override
    public boolean login(String name, String password) {
        Optional<LoginUser> user = this.loginUserRepo
                .findByName(name);
        return user.isPresent() &&
                this.encoder.matches(password, user.get().getPasswordHash());
    }
}
