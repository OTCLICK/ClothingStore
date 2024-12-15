package org.example.clothingstore.controllers;

import org.example.clothingstore.services.impl.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private final AuthService authService;

    public TestController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/authenticate")
    public ResponseEntity<String> testAuthentication(
            @RequestParam String username,
            @RequestParam String password) {
        boolean isAuthenticated = authService.authenticate(username, password);
        if (isAuthenticated) {
            return ResponseEntity.ok("Аутентификация успешна!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Ошибка аутентификации!");
        }
    }
}
