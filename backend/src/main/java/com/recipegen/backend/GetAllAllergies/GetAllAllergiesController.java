package com.recipegen.backend.GetAllAllergies;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/v1/recipe-gen/allergies")
public class GetAllAllergiesController {
    private final GetAllAllergiesService allergyService;

    public GetAllAllergiesController
            (GetAllAllergiesService allergyService) {
        this.allergyService = allergyService;
    }

    @Operation(summary = "Get all allergies",
            description = "Get all allergies by name in the database.")
    @ApiResponse(responseCode = "200", description = "The list with all allergies by name")
    @GetMapping
    public GetAllAllergiesApiResponse
            getAllAllergies() {
        return new GetAllAllergiesApiResponse(
                this.allergyService.getAllAllergies(), Instant.now());
    }
}
