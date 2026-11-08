package com.recipegen.backend.GetRestrictionsOfCategory;

import java.util.Set;

public interface IDietaryRestriction {
    Set<DietaryRestriction>
    getRestrictionsPerCategory(String category);
}
