package com.recipegen.backend.GetAllergyNamesOfUser;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserNameService {
    private final IUserName userNamePort;

    public UserNameService(IUserName userNamePort) {
        this.userNamePort = userNamePort;
    }

    public Set<String> getAllergiesForUser(String name) {
        return this.userNamePort.getAllergiesForUser(name);
    }
}
