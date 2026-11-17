package com.recipegen.backend.GenerateRecipe;

import com.recipegen.backend.GetRestrictionNamesOfUser.GenerateRecipeRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/v1/recipe-gen/generate")
public class GenerateRecipeController {
    public final GenerateRecipeService recipeService;

    public GenerateRecipeController
            (GenerateRecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Operation(summary = "Generate recipe",
               description = "Generate a recipe in accordance with user allergies, " +
                       "dietary restrictions, desired cultural region and available ingredients.")
    @ApiResponse(responseCode = "200",
                description = "The generated recipe and response timestamp, wrapped in a JSON")
    @GetMapping
    public GenerateRecipeApiResponse generate(GenerateRecipeRequest req) {
        return null;
    }

}
