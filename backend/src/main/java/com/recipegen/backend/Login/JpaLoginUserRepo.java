package com.recipegen.backend.Login;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaLoginUserRepo extends JpaRepository
        <@NonNull LoginUser, @NonNull UUID> {
    Optional<LoginUser> findByName
            (String name);
}
