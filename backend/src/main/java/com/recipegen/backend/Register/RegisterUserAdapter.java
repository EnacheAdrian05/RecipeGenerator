package com.recipegen.backend.Register;

import com.recipegen.backend.GetAllAllergies.Allergy;
import com.recipegen.backend.GetAllAllergies.JpaAllergyRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SuppressWarnings("unused")
@Component
public class RegisterUserAdapter implements IRegisterUser {
    private final JpaRegisterUserRepo userRepo;
    private final JpaAllergyRepo allergyRepo;

    public RegisterUserAdapter
            (JpaRegisterUserRepo userRepo,
             JpaAllergyRepo allergyRepo) {
        this.userRepo = userRepo;
        this.allergyRepo = allergyRepo;
    }


    @Override
    @Transactional
    public boolean register(String name, String email,
                            String password, Set<String> allergyNames) {
        if (this.userRepo.existsByName(name)) {
            return false;
        }

        Set<Allergy> allergies = new HashSet<>();

        for (String aName : allergyNames) {
            Optional<Allergy> allergy = this.allergyRepo.getAllergyByName(aName);

            if (allergy.isEmpty()) {
                return false;
            }

            allergies.add(allergy.get());
        }

        RegisterUser user = new RegisterUser(
                name, email, password, allergies);
        this.userRepo.save(user);
        return true;
    }
}
