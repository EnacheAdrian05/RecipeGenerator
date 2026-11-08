package com.recipegen.backend.GetAllergyNamesOfUser;

import java.util.Set;

public interface IUserNameAllergies {
    Set<String> getAllergiesForUser(String name);
}
