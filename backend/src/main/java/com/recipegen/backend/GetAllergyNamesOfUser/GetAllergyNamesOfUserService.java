package com.recipegen.backend.GetAllergyNamesOfUser;

import com.recipegen.backend.domain.interfaces.IUser;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GetAllergyNamesOfUserService {
    private final IUser userPort;

    public GetAllergyNamesOfUserService(IUser userPort) {
        this.userPort = userPort;
    }

    public Set<String> getAllergiesForUser(String name) {
        return this.userPort.getAllergies(name);
    }
}
