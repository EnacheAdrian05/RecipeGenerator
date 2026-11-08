package com.recipegen.backend.GetRestrictionNamesOfUser;

import java.util.Set;
import java.time.Instant;

public record GetRestrictionNamesOfUserApiResponse
        (Set<String> dietary_restrictions, Instant timestamp) {
}
