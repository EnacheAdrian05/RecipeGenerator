package com.recipegen.backend.domain.interfaces;

import java.util.Set;

public interface IUser {
    boolean register(String name,
                     String password,
                     Set<String> allergies,
                     Set<String> restrictions);
    boolean login(String name, String password);
    Set<String> getRestrictions(String userName);
    Set<String> getAllergies(String userName);
}
