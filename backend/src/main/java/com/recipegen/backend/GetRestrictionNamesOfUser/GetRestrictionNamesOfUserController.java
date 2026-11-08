package com.recipegen.backend.GetRestrictionNamesOfUser;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Set;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/v1/recipe-gen/dietary-restrictions/user")
public class GetRestrictionNamesOfUserController {
    public GetRestrictionNamesOfUserService userNameRestrictionsService;

    public GetRestrictionNamesOfUserController
            (GetRestrictionNamesOfUserService userNameRestrictionsService) {
        this.userNameRestrictionsService = userNameRestrictionsService;
    }

    @Operation(summary = "Get dietary restrictions of user",
            description = "Return user's dietary restrictions by name")
    @ApiResponse(responseCode = "200",
            description = "A set with all dietary restrictions by name")
    @GetMapping("/{name}")
    public GetRestrictionNamesOfUserApiResponse
        getRestrictionNamesOfUser(String name) {
        Set<String> restrictions = this
                .userNameRestrictionsService
                .getRestrictionNamesOfUser(name);
        return new GetRestrictionNamesOfUserApiResponse
                (restrictions, Instant.now());
    }
}
