package com.recipegen.backend.GetAllDietaryRestrictions;

import com.recipegen.backend.domain.entities.DietaryRestriction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/v1/recipe-gen/dietary-restrictions")
public class GetAllDietaryRestrictionsController {
    private final GetAllDietaryRestrictionsService restrictionsService;

    public GetAllDietaryRestrictionsController
            (GetAllDietaryRestrictionsService restrictionsService) {
        this.restrictionsService = restrictionsService;
    }

    @Operation(
            summary = "Get all dietary restrictions",
            description = "Return a list of all dietary restrictions, including their category" +
                    "(either `Personal` or `Religious`).")
    @ApiResponse(responseCode = "200",
            description = "List of dietary restrictions, with name and category")
    @GetMapping
    public GetAllDietaryRestrictionsApiResponse getRestrictions() {
        List<DietaryRestriction> restrictions = this
                .restrictionsService
                .getAllDietaryRestrictions();

        return new GetAllDietaryRestrictionsApiResponse
                (restrictions, Instant.now());
    }
}
