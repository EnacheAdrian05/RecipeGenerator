package com.recipegen.backend.GetAllAllergies;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllergyService {
    private final IAllergy allergyPort;

    public AllergyService(IAllergy allergyPort) {
        this.allergyPort = allergyPort;
    }

    public List<Allergy> getAllAllergies() {
        return this.allergyPort.getAllAllergies();
    }
}
