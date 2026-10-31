package com.recipegen.backend.Login;

import org.jspecify.annotations.NonNull;
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

    @GetMapping
    public ResponseEntity<@NonNull Map<String, String>> ping() {
        Map<String, String> res = new LinkedHashMap<>();
        res.put("response", "pong");
        res.put("timestamp", Instant.now().toString());

        return ResponseEntity.ok(res);
    }

    @PostMapping("/")
    public ResponseEntity<?> login
            (@RequestBody LoginUser user) {
        boolean logged = this.loginService.login
                (user.getName(), user.getPassword());
        if(!logged) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> res = new LinkedHashMap<>();
        res.put("logged", "Successfully logged in!");
        res.put("timestamp", Instant.now().toString());

        return ResponseEntity.ok(res);
    }
}