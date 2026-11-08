package com.recipegen.backend.GetAllergyNamesOfUser;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GetAllergyNamesOfUserService {
    private final IUserNameAllergies userNameAllergiesPort;

    public GetAllergyNamesOfUserService(IUserNameAllergies userNameAllergiesPort) {
        this.userNameAllergiesPort = userNameAllergiesPort;
    }

    public Set<String> getAllergiesForUser(String name) {
        return this.userNameAllergiesPort.getAllergiesForUser(name);
    }
}
