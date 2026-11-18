package com.recipegen.backend.domain.interfaces;

import com.recipegen.backend.domain.entities.DietaryRestriction;

import java.util.List;

public interface IDietaryRestriction {
    List<DietaryRestriction> getAllDietaryRestrictions();
}
