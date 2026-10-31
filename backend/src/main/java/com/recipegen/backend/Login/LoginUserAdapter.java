package com.recipegen.backend.Login;

import org.springframework.stereotype.Component;

import java.util.Optional;

@SuppressWarnings("unused")
@Component
public class LoginUserAdapter implements ILoginUser {
    private final JpaLoginUserRepo loginUserRepo;

    public LoginUserAdapter(JpaLoginUserRepo loginUserRepo) {
        this.loginUserRepo = loginUserRepo;
    }

    @Override
    public boolean login(String name, String password) {
        Optional<LoginUser> user = this.loginUserRepo
                .findByNameAndPassword(name, password);
        return user.isPresent();
    }
}
