package com.recipegen.backend.GetAllAllergies;

import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/v1/recipe-gen/allergies")
public class GetAllAllergiesController {
    private final GetAllAllergiesService allergyService;

    public GetAllAllergiesController
            (GetAllAllergiesService allergyService) {
        this.allergyService = allergyService;
    }

    @GetMapping
    public ResponseEntity<@NonNull Map<String, String>> ping() {
        Map<String, String> res = new LinkedHashMap<>();
        res.put("response", "pong");
        res.put("timestamp", Instant.now().toString());

        return ResponseEntity.ok(res);
    }

    @GetMapping("/")
    public ResponseEntity<@NonNull Map<String, Object>>
            getAllAllergies() {
        Map<String, Object> res = new LinkedHashMap<>();
        List<Allergy> allergies = this.allergyService
                .getAllAllergies();
        res.put("allergies", allergies);
        res.put("timestamp", Instant.now().toString());

        return ResponseEntity.ok(res);
    }
}
