package com.recipegen.backend.GetRestrictionsPerCategory;

import java.util.Set;

public interface IDietaryRestriction {
    Set<DietaryRestriction>
        getDietaryRestrictionsCategory(String category);
}
