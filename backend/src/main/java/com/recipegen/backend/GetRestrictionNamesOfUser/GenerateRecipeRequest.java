package com.recipegen.backend.GetRestrictionNamesOfUser;

import java.util.Set;
import java.util.Map;

public record GenerateRecipeRequest
        (String userName, Set<String> allergies,
         Set<String> restrictions, String culturalRegion,
         Map<String, Object> ingredients) {
}
