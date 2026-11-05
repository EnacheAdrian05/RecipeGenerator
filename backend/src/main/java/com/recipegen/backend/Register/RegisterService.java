package com.recipegen.backend.Register;

import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class RegisterService {
    public IRegisterUser registerPort;

    public RegisterService(IRegisterUser registerPort) {
        this.registerPort = registerPort;
    }

    public boolean register(String name, String email,
                            String password, Set<String> allergies) {
        return registerPort.register(name, email, password, allergies);
    }
}
