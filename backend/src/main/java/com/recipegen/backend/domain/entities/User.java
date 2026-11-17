package com.recipegen.backend.domain.entities;

import com.fasterxml.uuid.Generators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * A user has its own unique name, email and password hash,
 * and is identified by a unique ID.<br/>
 * Evidently, a user can have multiple allergies and dietary restrictions,
 * which themselves can be associated to multiple distinct users.
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "users")
public class User {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id",
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
    @Column(name = "password_hash",
            nullable = false)
    @Size(max = 255)
    private String passwordHash;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "has_allergies",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "allergy_id")
    )
    private Set<Allergy> allergies = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "has_restrictions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "restriction_id")
    )
    private Set<DietaryRestriction> restrictions = new HashSet<>();

    protected User() {}

    public User(String name, String email,
                String passwordHash,
                Set<Allergy> allergies,
                Set<DietaryRestriction> restrictions) {
        this.setName(name.trim());
        this.setPasswordHash(passwordHash);
        this.id = Generators.timeBasedEpochGenerator().generate();
        this.email = email;
        this.allergies = allergies;
        this.restrictions = restrictions;
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

    public void setPasswordHash(String passwordHash) {
        if (passwordHash.length() < 60 ||
                passwordHash.length() > 255) {
            throw new IllegalArgumentException("Invalid hash!");
        }

        this.passwordHash = passwordHash;
    }

    public void setAllergies(Set<Allergy> allergies) {
        this.allergies = allergies;
    }

    public void setRestrictions
            (Set<DietaryRestriction> restrictions) {
        int religiousRestrictionsCount = Math.toIntExact(restrictions.stream()
                .filter(r -> Objects.equals(r.getCategory(), "Religious"))
                .count());
        if (religiousRestrictionsCount > 1) {
            throw new IllegalArgumentException
                    ("Cannot have multiple religious dietary restrictions!");
        }

        this.restrictions = restrictions;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public Set<Allergy> getAllergies() {
        return this.allergies;
    }

    public Set<DietaryRestriction> getDietaryRestrictions() {
        return this.restrictions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof User other)) {
            return false;
        }

        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return this.id == null ? 0 : this.id.hashCode();
    }
}
