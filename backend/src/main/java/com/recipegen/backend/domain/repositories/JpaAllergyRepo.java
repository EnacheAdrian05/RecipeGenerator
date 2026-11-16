package com.recipegen.backend.domain.repositories;

import com.recipegen.backend.domain.entities.Allergy;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaAllergyRepo extends JpaRepository
        <@NonNull Allergy, @NonNull UUID>{
    Optional<Allergy> getAllergyByName(String name);
}
