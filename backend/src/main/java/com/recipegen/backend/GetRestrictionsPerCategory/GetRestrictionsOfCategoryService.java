package com.recipegen.backend.GetRestrictionsPerCategory;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GetRestrictionsOfCategoryService {
    private final IDietaryRestriction restrictionsPort;

    public GetRestrictionsOfCategoryService
            (IDietaryRestriction restrictionsPort) {
        this.restrictionsPort = restrictionsPort;
    }

    public Set<DietaryRestriction>
        getRestrictionsOfCategory(String category) {
        return this.restrictionsPort
                .getDietaryRestrictionsCategory(category);
    }
}
