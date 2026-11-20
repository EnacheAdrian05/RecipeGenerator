package com.recipegen.backend.GenerateRecipe;

import java.util.Map;

public record GenerateRecipeRequest
        (String userName, String culturalRegion,
         Map<String, Object> ingredients) {
}
