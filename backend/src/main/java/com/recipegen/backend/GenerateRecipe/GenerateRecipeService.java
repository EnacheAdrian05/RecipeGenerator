package com.recipegen.backend.GenerateRecipe;

import com.recipegen.backend.domain.interfaces.IUser;
import org.springframework.stereotype.Service;

@Service
public class GenerateRecipeService {
    private final IUser userPort;

    public GenerateRecipeService
            (IUser userPort) {
        this.userPort = userPort;
    }
}
