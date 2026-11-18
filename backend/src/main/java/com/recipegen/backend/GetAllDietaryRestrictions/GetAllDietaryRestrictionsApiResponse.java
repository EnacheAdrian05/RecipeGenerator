package com.recipegen.backend.GetAllDietaryRestrictions;

import com.recipegen.backend.domain.entities.DietaryRestriction;

import java.util.List;
import java.time.Instant;

public record GetAllDietaryRestrictionsApiResponse(
        List<DietaryRestriction> restrictions,
        Instant timestamp
) {
}
