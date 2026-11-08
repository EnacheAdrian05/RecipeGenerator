package com.recipegen.backend.GetRestrictionsOfCategory;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/v1/recipe-gen/dietary-restrictions")
public class GetRestrictionsOfCategoryController {
    private final GetRestrictionsOfCategoryService restrictionsService;

    public GetRestrictionsOfCategoryController
            (GetRestrictionsOfCategoryService restrictionsService) {
        this.restrictionsService = restrictionsService;
    }

    @Operation(
            summary = "Get restrictions of category",
            description = "For a given category ('Personal' or 'Religious'), " +
                    "return a list of all dietary restriction names in that category.")
    @ApiResponse(responseCode = "200",
            description = "List of dietary restriction names")
    @GetMapping
    public GetRestrictionsOfCategoryApiResponse getRestrictions(
            @Parameter(description = "Category of dietary restrictions." +
                    " One of: 'Personal' or 'Religious'.",
                    example = "Personal")
            @RequestParam String category) {
        Set<String> restrictions = this
                .restrictionsService
                .getRestrictionsOfCategory(category)
                .stream()
                .map(DietaryRestriction::getName)
                .collect(Collectors.toSet());

        return new GetRestrictionsOfCategoryApiResponse
                (restrictions, Instant.now());
    }
}
