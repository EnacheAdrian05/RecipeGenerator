package com.recipegen.backend.domain.adapters;

import com.recipegen.backend.domain.entities.Allergy;
import com.recipegen.backend.domain.interfaces.IAllergy;
import com.recipegen.backend.domain.repositories.JpaAllergyRepo;
import org.springframework.stereotype.Component;

import java.util.List;

@SuppressWarnings("unused")
@Component
public class AllergyAdapter implements IAllergy {
    private final JpaAllergyRepo allergyRepo;

    public AllergyAdapter(JpaAllergyRepo allergyRepo) {
        this.allergyRepo = allergyRepo;
    }

    @Override
    public List<Allergy> getAllAllergies() {
        return this.allergyRepo.findAll();
    }
}
