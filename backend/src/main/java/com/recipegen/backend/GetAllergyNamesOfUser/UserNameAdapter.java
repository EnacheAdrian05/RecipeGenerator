package com.recipegen.backend.GetAllergyNamesOfUser;

import com.recipegen.backend.GetAllAllergies.Allergy;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@Component
public class UserNameAdapter implements IUserName {
    private final JpaUserNameRepo userNameRepo;

    public UserNameAdapter(JpaUserNameRepo userNameRepo) {
        this.userNameRepo = userNameRepo;
    }

    @Override
    public Set<String> getAllergiesForUser(String name) {
        Optional<UserName> userOptional = this.userNameRepo
                .getUserByName(name);
        if (userOptional.isEmpty()) {
            return new HashSet<>();
        }

        UserName user = userOptional.get();

        return user.getAllergies()
                .stream().map(Allergy::getName)
                .collect(Collectors.toSet());
    }
}
