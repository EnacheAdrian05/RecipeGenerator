package com.recipegen.backend.GetRestrictionsPerCategory;

import java.util.Set;
import java.time.Instant;

public record GetRestrictionsOfCategoryResponse(
        Set<String> restrictions, Instant timestamp
) {
}
