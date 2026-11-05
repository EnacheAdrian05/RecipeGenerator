package com.recipegen.backend.Register;

import com.fasterxml.uuid.Generators;
import com.recipegen.backend.GetAllAllergies.Allergy;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * A user registers with a valid name, email, password
 * and (optionally) a collections of allergies they have.
 */
@Entity
@SuppressWarnings("unused")
@Table(name = "users")
public class RegisterUser {
    private final static String PASSWORD_REGEX =
            "^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,24}$";
    private final static java.util.regex.Pattern PATTERN =
            java.util.regex.Pattern.compile(PASSWORD_REGEX);
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", columnDefinition = "CHAR(36)",
            unique = true, nullable = false)
    private UUID id;

    @NotNull
    @Size(max = 64)
    @Column(name = "name",
            unique = true, nullable = false)
    private String name;

    @NotNull
    @Email
    @Column(name = "email",
            nullable = false)
    private String email;

    @NotNull
    @Pattern(regexp = PASSWORD_REGEX,
             message = "Password must be 8-24 characters long," +
                     "must contain at least a number, a capital letter " +
                     "and a special character")
    @Column(name = "password",
            nullable = false)
    @Size(min = 8, max = 24)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ailments",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "allergy_id")
    )
    private Set<Allergy> allergies = new HashSet<>();

    protected RegisterUser() {}

    public RegisterUser(String name, String email,
                        String password, Set<Allergy> allergies) {
        this.setName(name.trim());
        this.setPassword(password);
        this.id = Generators.timeBasedEpochGenerator().generate();
        this.email = email;
        this.allergies = allergies;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()
                || name.length() > 64) {
            throw new IllegalArgumentException("Invalid name!");
        }

        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        if (!PATTERN.matcher(password).matches()) {
            throw new IllegalArgumentException("Invalid password!");
        }

        this.password = password;
    }

    public void setAllergies(Set<Allergy> allergies) {
        this.allergies = allergies;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public Set<Allergy> getAllergies() {
        return this.allergies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof RegisterUser other)) {
            return false;
        }

        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return this.id == null ? 0 : this.id.hashCode();
    }
}
