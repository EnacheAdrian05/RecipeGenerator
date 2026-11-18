package com.recipegen.backend.Register;

import com.recipegen.backend.domain.interfaces.IUser;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class RegisterService {
    public IUser userPort;

    public RegisterService(IUser userPort) {
        this.userPort = userPort;
    }

    public boolean register(String name,
                            String password,
                            Set<String> allergies,
                            Set<String> restrictions) {
        return this.userPort.register
                (name, password, allergies, restrictions);
    }
}
