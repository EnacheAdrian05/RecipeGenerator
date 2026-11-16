package com.recipegen.backend.domain.interfaces;

import com.recipegen.backend.domain.entities.DietaryRestriction;

import java.util.Set;

public interface IDietaryRestriction {
    Set<DietaryRestriction>
    getRestrictionsPerCategory(String category);
}
