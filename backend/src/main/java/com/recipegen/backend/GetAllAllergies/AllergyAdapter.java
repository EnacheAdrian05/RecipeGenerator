package com.recipegen.backend.GetAllAllergies;

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
