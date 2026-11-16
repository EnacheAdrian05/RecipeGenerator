package com.recipegen.backend.Login;

import com.recipegen.backend.domain.interfaces.IUser;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final IUser userPort;

    public LoginService(IUser userPort) {
        this.userPort = userPort;
    }

    boolean login(String name, String password) {
        return this.userPort.login(name, password);
    }
}
