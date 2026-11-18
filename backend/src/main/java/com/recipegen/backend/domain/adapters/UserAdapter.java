package com.recipegen.backend.domain.adapters;

import com.recipegen.backend.domain.entities.Allergy;
import com.recipegen.backend.domain.entities.DietaryRestriction;
import com.recipegen.backend.domain.entities.User;
import com.recipegen.backend.domain.interfaces.IUser;
import com.recipegen.backend.domain.repositories.JpaAllergyRepo;
import com.recipegen.backend.domain.repositories.JpaDietaryRestrictionRepo;
import com.recipegen.backend.domain.repositories.JpaUserRepo;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@Component
public class UserAdapter implements IUser {
    private final JpaUserRepo userRepo;
    private final JpaAllergyRepo allergyRepo;
    private final JpaDietaryRestrictionRepo restrictionRepo;
    private final PasswordEncoder encoder;

    public UserAdapter(JpaUserRepo userRepo,
                       JpaAllergyRepo allergyRepo,
                       JpaDietaryRestrictionRepo restrictionRepo,
                       PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.allergyRepo = allergyRepo;
        this.restrictionRepo = restrictionRepo;
        this.encoder = encoder;
    }

    @Override
    @Transactional
    public boolean register(String name,
                            String password,
                            Set<String> allergies_,
                            Set<String> restrictions_) {
        if (this.userRepo.existsByName(name)) {
            return false;
        }

        String passwordHash = this.encoder.encode(password);
        Set<Allergy> allergies = new HashSet<>();
        for (String aName : allergies_) {
            Optional<Allergy> allergy = this.allergyRepo.getAllergyByName(aName);

            if (allergy.isEmpty()) {
                return false;
            }

            allergies.add(allergy.get());
        }

        Set<DietaryRestriction> restrictions = new HashSet<>();
        for (String dName: restrictions_) {
            Optional<DietaryRestriction> restriction =
                    this.restrictionRepo.getRestrictionByName(dName);

            if (restriction.isEmpty()) {
                return false;
            }

            restrictions.add(restriction.get());
        }

        User user = new User(name, passwordHash,
                allergies, restrictions);
        this.userRepo.save(user);
        return true;
    }

    @Override
    public boolean login(String name, String password) {
        Optional<User> user = this.userRepo
                .findByName(name);
        return user.isPresent() &&
                this.encoder.matches(password, user.get().getPasswordHash());
    }

    @Override
    public Set<String> getAllergies(String name) {
        Optional<User> userOptional = this.userRepo
                .findByName(name);
        if (userOptional.isEmpty()) {
            return new HashSet<>();
        }

        User user = userOptional.get();
        return user.getAllergies()
                .stream().map(Allergy::getName)
                .collect(Collectors.toSet());
    }
    @Override
    public Set<String> getRestrictions(String name) {
        Optional<User> userOptional =
                this.userRepo.findByName(name);
        if (userOptional.isEmpty()) {
            return new HashSet<>();
        }

        User user = userOptional.get();
        return user.getDietaryRestrictions()
                .stream()
                .map(DietaryRestriction::getName)
                .collect(Collectors.toSet());
    }
}
