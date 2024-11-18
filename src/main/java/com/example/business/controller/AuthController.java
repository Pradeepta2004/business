package com.example.business.controller;

import com.example.business.model.Tenant;
import com.example.business.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private TenantService tenantService;

    // Registration endpoint
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Tenant tenant) {
        tenantService.registerUser(tenant);
        return ResponseEntity.ok("User registered successfully");
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Tenant loginRequest) {
        Tenant tenant = tenantService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
        if (tenant != null) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
}