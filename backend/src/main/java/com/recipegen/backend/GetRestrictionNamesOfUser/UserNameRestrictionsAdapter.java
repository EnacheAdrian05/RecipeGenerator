package com.recipegen.backend.GetRestrictionNamesOfUser;

import com.recipegen.backend.GetRestrictionsOfCategory.DietaryRestriction;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@Component
public class UserNameRestrictionsAdapter
        implements IUserNameRestrictions {
    public JpaUserNameRestrictionsRepo userNameRestrictionsRepo;

    public UserNameRestrictionsAdapter
            (JpaUserNameRestrictionsRepo userNameRestrictionsRepo) {
        this.userNameRestrictionsRepo = userNameRestrictionsRepo;
    }

    @Override
    public Set<String> getRestrictionNamesOfUser(String name) {
        Optional<UserNameRestrictions> userOptional =
                this.userNameRestrictionsRepo.getUserByName(name);
        if (userOptional.isEmpty()) {
            return new HashSet<>();
        }

        UserNameRestrictions user = userOptional.get();
        return user.getDietaryRestrictions()
                .stream()
                .map(DietaryRestriction::getName)
                .collect(Collectors.toSet());
    }
}
