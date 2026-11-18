package com.recipegen.backend.GetAllDietaryRestrictions;

import com.recipegen.backend.domain.entities.DietaryRestriction;
import com.recipegen.backend.domain.interfaces.IDietaryRestriction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllDietaryRestrictionsService {
    private final IDietaryRestriction restrictionsPort;

    public GetAllDietaryRestrictionsService
            (IDietaryRestriction restrictionsPort) {
        this.restrictionsPort = restrictionsPort;
    }

    public List<DietaryRestriction>
        getRestrictionsOfCategory() {
        return this.restrictionsPort
                .getAllDietaryRestrictions();
    }
}
