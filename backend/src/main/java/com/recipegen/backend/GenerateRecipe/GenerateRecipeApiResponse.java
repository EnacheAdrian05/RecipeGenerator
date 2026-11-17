package com.recipegen.backend.GenerateRecipe;

import java.time.Instant;

public record GenerateRecipeApiResponse
        (String recipe, Instant timestamp) {
}
