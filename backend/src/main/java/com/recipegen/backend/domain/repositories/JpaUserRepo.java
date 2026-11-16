package com.recipegen.backend.domain.repositories;

import com.recipegen.backend.domain.entities.User;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaUserRepo extends JpaRepository
    <@NonNull User, @NonNull UUID> {
    boolean existsByName(String name);
    Optional<User> findByName(String name);
}
