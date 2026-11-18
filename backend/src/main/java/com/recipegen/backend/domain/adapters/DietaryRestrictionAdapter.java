package com.recipegen.backend.domain.adapters;

import com.recipegen.backend.domain.entities.DietaryRestriction;
import com.recipegen.backend.domain.interfaces.IDietaryRestriction;
import com.recipegen.backend.domain.repositories.JpaDietaryRestrictionRepo;
import org.springframework.stereotype.Component;

import java.util.List;

@SuppressWarnings("unused")
@Component
public class DietaryRestrictionAdapter implements IDietaryRestriction {
    private final JpaDietaryRestrictionRepo restrictionRepo;

    public DietaryRestrictionAdapter
            (JpaDietaryRestrictionRepo restrictionRepo) {
        this.restrictionRepo = restrictionRepo;
    }

    @Override
    public List<DietaryRestriction>
    getAllDietaryRestrictions() {
        return this.restrictionRepo.findAll();
    }
}
