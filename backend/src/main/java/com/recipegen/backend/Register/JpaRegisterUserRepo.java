package com.recipegen.backend.Register;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaRegisterUserRepo extends JpaRepository
    <@NonNull RegisterUser, @NonNull UUID> {
    boolean existsByName(String name);
}
