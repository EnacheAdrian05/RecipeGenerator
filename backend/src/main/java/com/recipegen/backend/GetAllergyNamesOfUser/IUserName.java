package com.recipegen.backend.GetAllergyNamesOfUser;

import java.util.Set;

public interface IUserName {
    Set<String> getAllergiesForUser(String name);
}
