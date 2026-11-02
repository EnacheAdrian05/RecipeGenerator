package com.recipegen.backend.GetAllAllergies;
import java.time.Instant;
import java.util.List;

public record GetAllAllergiesApiResponse(
        List<Allergy> allergies, Instant timestamp) {
}
