package com.recipegen.backend.domain.repositories;

import com.recipegen.backend.domain.entities.DietaryRestriction;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface JpaDietaryRestrictionRepo extends JpaRepository
    <@NonNull DietaryRestriction, @NonNull UUID> {
    Optional<DietaryRestriction> getRestrictionByName
            (String name);
}
