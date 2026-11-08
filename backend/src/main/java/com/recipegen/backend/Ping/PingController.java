package com.recipegen.backend.Ping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/v1/recipe-gen/ping")
public class PingController {
    @Operation(summary = "Ping",
            description = "Check whether server is running.")
    @ApiResponse(responseCode = "200",
            description = "Server is running",
            content = @Content(
                 mediaType = "application/json",
                 examples = @ExampleObject(
                         value = "{\"message\": \"pong\"," +
                                 "\"timestamp\": \"2026-11-08T19:38:03Z\"}"
                 )
            )
    )
    @GetMapping
    public PingApiResponse ping() {
        return new PingApiResponse(
                "pong", Instant.now()
        );
    }
}
