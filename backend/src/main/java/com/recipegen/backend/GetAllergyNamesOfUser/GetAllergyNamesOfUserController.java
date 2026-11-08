package com.recipegen.backend.GetAllergyNamesOfUser;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Set;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/v1/recipe-gen/allergies/user")
public class GetAllergyNamesOfUserController {
    private final GetAllergyNamesOfUserService userNameAllergiesService;

    public GetAllergyNamesOfUserController
            (GetAllergyNamesOfUserService userNameAllergiesService) {
        this.userNameAllergiesService = userNameAllergiesService;
    }

    @Operation(summary = "Get allergies of user",
            description = "Return user's allergies by name")
    @ApiResponse(responseCode = "200",
            description = "A set with all allergies by name")
    @GetMapping("/{name}")
    public GetAllergiesNamesOfUserApiResponse getAllergyNamesForUser
            (@PathVariable String name) {
        Set<String> allergies = this.userNameAllergiesService
                .getAllergiesForUser(name);
        return new GetAllergiesNamesOfUserApiResponse(
                allergies, Instant.now()
        );
    }
}
