package com.recipegen.backend.GetAllergyNamesOfUser;

import com.recipegen.backend.GetAllAllergies.Allergy;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@Component
public class UserNameAllergiesAdapter implements IUserNameAllergies {
    private final JpaUserNameAllergiesRepo userNameAllergiesRepo;

    public UserNameAllergiesAdapter(JpaUserNameAllergiesRepo userNameRepo) {
        this.userNameAllergiesRepo = userNameRepo;
    }

    @Override
    public Set<String> getAllergiesForUser(String name) {
        Optional<UserNameAllergies> userOptional = this.userNameAllergiesRepo
                .getUserByName(name);
        if (userOptional.isEmpty()) {
            return new HashSet<>();
        }

        UserNameAllergies user = userOptional.get();
        return user.getAllergies()
                .stream().map(Allergy::getName)
                .collect(Collectors.toSet());
    }
}
