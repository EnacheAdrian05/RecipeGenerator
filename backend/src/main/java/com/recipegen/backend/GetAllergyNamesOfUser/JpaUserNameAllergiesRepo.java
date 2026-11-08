package com.recipegen.backend.GetAllergyNamesOfUser;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaUserNameAllergiesRepo extends JpaRepository
    <@NonNull UserNameAllergies, @NonNull UUID> {
    Optional<UserNameAllergies> getUserByName(String name);
}
