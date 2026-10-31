package com.recipegen.backend.GetAllAllergies;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaAllergyRepo extends JpaRepository
        <@NonNull Allergy, @NonNull UUID>{
}
