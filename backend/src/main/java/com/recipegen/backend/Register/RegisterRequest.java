package com.recipegen.backend.Register;

import java.util.Set;

public record RegisterRequest
        (String name, String email,
         String password, Set<String> allergies) {
}
