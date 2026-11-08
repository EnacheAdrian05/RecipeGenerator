package com.recipegen.backend.GetRestrictionsOfCategory;

import java.util.Set;
import java.time.Instant;

public record GetRestrictionsOfCategoryApiResponse(
        Set<String> restrictions, Instant timestamp
) {
}
