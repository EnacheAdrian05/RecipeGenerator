package com.recipegen.backend.GetAllergyNamesOfUser;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaUserNameRepo extends JpaRepository
    <@NonNull UserName, @NonNull UUID> {
    Optional<UserName> getUserByName(String name);
}
