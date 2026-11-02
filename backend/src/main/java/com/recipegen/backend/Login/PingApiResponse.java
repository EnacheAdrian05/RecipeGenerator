package com.recipegen.backend.Login;

import java.time.Instant;

public record PingApiResponse
        (String message, Instant timeline) {
}
