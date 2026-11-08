package com.recipegen.backend.GetRestrictionsOfCategory;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface JpaDietaryRestrictionRepo extends JpaRepository
    <@NonNull DietaryRestriction, @NonNull UUID> {
    Set<DietaryRestriction> getNamesByCategory
            (String category);
}
