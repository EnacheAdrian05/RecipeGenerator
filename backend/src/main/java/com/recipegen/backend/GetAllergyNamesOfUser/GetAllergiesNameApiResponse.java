package com.recipegen.backend.GetAllergyNamesOfUser;

import java.time.Instant;
import java.util.Set;

public record GetAllergiesNameApiResponse(
        Set<String> allergies, Instant timestamp
) {
}
