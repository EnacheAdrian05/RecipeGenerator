package com.recipegen.backend.GetAllAllergies;
import java.time.Instant;

public record PingResponse
        (String message, Instant timestamp) {
}
