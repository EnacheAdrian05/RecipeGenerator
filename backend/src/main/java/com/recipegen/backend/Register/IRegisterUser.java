package com.recipegen.backend.Register;

import java.util.Set;

public interface IRegisterUser {
    boolean register(String name, String email,
                     String password, Set<String> allergies);
}
