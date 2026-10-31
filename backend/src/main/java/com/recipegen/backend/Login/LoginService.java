package com.recipegen.backend.Login;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final ILoginUser loginUserPort;

    public LoginService(ILoginUser loginUserPort) {
        this.loginUserPort = loginUserPort;
    }

    boolean login(String name, String password) {
        return this.loginUserPort.login(name, password);
    }
}
