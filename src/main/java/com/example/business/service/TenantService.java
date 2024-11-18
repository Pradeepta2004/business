package com.example.business.service;

import com.example.business.model.Tenant;
import com.example.business.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // Directly autowire BCryptPasswordEncoder

    public Tenant registerUser(Tenant tenant) {
        tenant.setPassword(passwordEncoder.encode(tenant.getPassword()));
        return tenantRepository.save(tenant);
    }

    public Tenant loginUser(String email, String password) {
        return tenantRepository.findByEmail(email)
                .filter(tenant -> passwordEncoder.matches(password, tenant.getPassword()))
                .orElse(null);
    }
}