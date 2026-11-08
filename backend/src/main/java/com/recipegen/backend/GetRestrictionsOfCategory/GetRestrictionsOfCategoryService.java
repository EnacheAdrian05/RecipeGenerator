package com.recipegen.backend.GetRestrictionsOfCategory;

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
                .getRestrictionsPerCategory(category);
    }
}
