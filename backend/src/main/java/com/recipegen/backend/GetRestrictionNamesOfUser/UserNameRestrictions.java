package com.recipegen.backend.GetRestrictionNamesOfUser;

import com.recipegen.backend.GetRestrictionsOfCategory.DietaryRestriction;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * For the SELECT query to work the only external variable required is
 * the username. Dietary restrictions set is provided for many-to-many relationship table.
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "users")
public class UserNameRestrictions {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", columnDefinition = "CHAR(36)",
            unique = true, nullable = false)
    private UUID id;

    @NotNull
    @Size(max = 64)
    @Column(name = "name", unique = true,
            nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "has_restriction",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "restriction_id")
    )
    private Set<DietaryRestriction> dietaryRestrictions
            = new HashSet<>();

    protected UserNameRestrictions() {}

    public UserNameRestrictions
            (String name, Set<DietaryRestriction> dietaryRestrictions) {
        this.setName(name);
        this.dietaryRestrictions = dietaryRestrictions;
    }

    public void setName(String name) {
        if (name == null ||
                name.isBlank() || name.length() > 64) {
            throw new IllegalArgumentException("Invalid name!");
        }

        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Set<DietaryRestriction> getDietaryRestrictions() {
        return this.dietaryRestrictions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof UserNameRestrictions other)) {
            return false;
        }

        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return this.id == null ? 0 : this.id.hashCode();
    }
}
