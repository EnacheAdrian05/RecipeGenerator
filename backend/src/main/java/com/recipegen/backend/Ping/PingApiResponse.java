package com.recipegen.backend.Ping;

import java.time.Instant;

public record PingApiResponse
        (String message, Instant timestamp) {
}
