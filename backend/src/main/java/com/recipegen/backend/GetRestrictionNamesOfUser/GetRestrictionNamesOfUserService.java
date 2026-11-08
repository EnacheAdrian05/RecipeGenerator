package com.recipegen.backend.GetRestrictionNamesOfUser;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GetRestrictionNamesOfUserService {
    public IUserNameRestrictions userNameRestrictionsPort;

    public  GetRestrictionNamesOfUserService
            (IUserNameRestrictions userNameRestrictionsPort) {
        this.userNameRestrictionsPort = userNameRestrictionsPort;
    }

    public Set<String> getRestrictionNamesOfUser(String name) {
        return this.userNameRestrictionsPort
                .getRestrictionNamesOfUser(name);
    }
}
