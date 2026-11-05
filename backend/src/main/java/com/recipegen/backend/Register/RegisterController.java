package com.recipegen.backend.Register;

import com.recipegen.backend.GetAllAllergies.PingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/v1/recipe-gen/register")
public class RegisterController {
    public RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping
    @Operation(summary = "Ping",
            description = "Check if server is running.")
    @ApiResponse(responseCode = "200", description = "Server is running")
    public PingResponse ping() {
        return new PingResponse("pong", Instant.now());
    }

    @PostMapping("/")
    @Operation(summary = "Register",
            description = "Add a user and their allergies. Returns 409 on failure.")
    @ApiResponse(responseCode = "200", description = "Registration successful",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = "{\"registered\": true, " +
                                    "\"timestamp\": \"2026-11-04T19:59:03Z\"}"
                    )
            )
    )
    @ApiResponse(responseCode = "409", description = "User already exists",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = "{\"registered\": false, " +
                                    "\"error\": \"A user with this name already exists\", " +
                                    " \"timestamp\": \"2026-11-04T20:00:03Z\"}"
                    )
            )
    )
    public ResponseEntity<@NonNull Map<String, Object>> register
            (@RequestBody RegisterRequest user) {
        Map<String, Object> res = new LinkedHashMap<>();
        boolean registered = this.registerService.register(
                user.name(), user.email(),
                user.password(), user.allergies());

        if (!registered) {
            res.put("registered", false);
            res.put("error", "A user with this name already exists");
            res.put("timestamp", Instant.now());

            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(res);
        }

        res.put("registered", true);
        res.put("timestamp", Instant.now());

        return ResponseEntity.ok(res);
    }
}
