package com.recipegen.backend.Register;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaRegisterUserRepo extends JpaRepository
    <@NonNull RegisterUser, @NonNull UUID> {
    boolean existsByName(String name);
}
