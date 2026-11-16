package com.recipegen.backend.GetAllAllergies;
import com.recipegen.backend.domain.entities.Allergy;

import java.time.Instant;
import java.util.List;

public record GetAllAllergiesApiResponse(
        List<Allergy> allergies, Instant timestamp) {
}
