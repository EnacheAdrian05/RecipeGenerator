package com.recipegen.backend.GetRestrictionsPerCategory;

import org.springframework.stereotype.Component;

import java.util.Set;

@SuppressWarnings("unused")
@Component
public class DietaryRestrictionAdapter implements IDietaryRestriction {
    private final JpaDietaryRestrictionRepo restrictionRepo;

    public DietaryRestrictionAdapter
            (JpaDietaryRestrictionRepo restrictionRepo) {
        this.restrictionRepo = restrictionRepo;
    }

    @Override
    public Set<DietaryRestriction>
        getDietaryRestrictionsCategory(String category) {
        return this.restrictionRepo.getNamesByCategory(category);
    }
}
