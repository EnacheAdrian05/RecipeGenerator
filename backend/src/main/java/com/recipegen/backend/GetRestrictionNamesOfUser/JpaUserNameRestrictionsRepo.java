package com.recipegen.backend.GetRestrictionNamesOfUser;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaUserNameRestrictionsRepo
    extends JpaRepository
        <@NonNull UserNameRestrictions, @NonNull UUID> {
    Optional<UserNameRestrictions> getUserByName(String name);
}
