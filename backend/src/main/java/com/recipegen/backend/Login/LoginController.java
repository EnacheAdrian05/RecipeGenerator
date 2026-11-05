package com.recipegen.backend.Login;

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
@RequestMapping("/api/v1/recipe-gen/login")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @Operation(summary = "Ping",
            description = "Check if server is running.")
    @ApiResponse(responseCode = "200", description = "Server is running")
    @GetMapping
    public PingResponse ping() {
        return new PingResponse("pong", Instant.now());
    }

    @Operation(summary = "Login",
            description = "Authenticate a user. Returns 401 on failure.")
    @ApiResponse(responseCode = "200", description = "Login successful",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                        value = "{\"logged\": true, " +
                                "\"timestamp\": \"2026-11-01T12:33:03Z\"}"
                )
            )
    )
    @ApiResponse(responseCode = "401", description = "Invalid credentials",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = "{\"logged\": false, " +
                                    "\"error\": \"Invalid username or password\", " +
                                    " \"timestamp\": \"2026-11-01T12:33:03Z\"}"
                    )
            )
    )
    @PostMapping("/")
    public ResponseEntity<@NonNull Map<String, Object>> login
            (@RequestBody LoginUser user) {
        Map<String, Object> res = new LinkedHashMap<>();
        boolean logged = this.loginService.login
                (user.getName(), user.getPassword());
        if(!logged) {
            res.put("logged", false);
            res.put("error", "Invalid username or password");
            res.put("timestamp", Instant.now());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(res);
        }

        res.put("logged", true);
        res.put("timestamp", Instant.now());

        return ResponseEntity.ok(res);
    }
}