package com.recipegen.backend.GetRestrictionsOfCategory;

import com.recipegen.backend.domain.entities.DietaryRestriction;
import com.recipegen.backend.domain.interfaces.IDietaryRestriction;
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
