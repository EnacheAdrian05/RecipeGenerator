package com.recipegen.backend.GetRestrictionNamesOfUser;

import com.recipegen.backend.domain.interfaces.IUser;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GetRestrictionNamesOfUserService {
    public IUser userPort;

    public  GetRestrictionNamesOfUserService
            (IUser userPort) {
        this.userPort = userPort;
    }

    public Set<String> getRestrictionNamesOfUser(String name) {
        return this.userPort
                .getRestrictions(name);
    }
}
