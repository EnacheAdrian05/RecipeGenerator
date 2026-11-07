package com.recipegen.backend.GetAllergyNamesOfUser;

import com.recipegen.backend.GetAllAllergies.PingResponse;
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
@RequestMapping("/api/v1/recipe-gen/allergies/user/")
public class GetAllergyNamesController {
    private final UserNameService userNameService;

    public GetAllergyNamesController
            (UserNameService userNameService) {
        this.userNameService = userNameService;
    }

    @Operation(summary = "Ping",
            description = "Check if function is running.")
    @ApiResponse(responseCode = "200", description = "Server is running")
    @GetMapping
    public PingResponse ping() {
        return new PingResponse("pong", Instant.now());
    }

    @Operation(summary = "Get allergies of user",
            description = "Return a set of user's allergies by name")
    @ApiResponse(responseCode = "200",
            description = "The list with all allergies by name")
    @GetMapping("/{name}")
    public GetAllergiesNameApiResponse getAllergyNamesForUser
            (@PathVariable String name) {
        Set<String> allergies = this.userNameService
                .getAllergiesForUser(name);
        return new GetAllergiesNameApiResponse(
                allergies, Instant.now()
        );
    }
}
