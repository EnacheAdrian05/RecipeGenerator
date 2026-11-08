package com.recipegen.backend.GetAllergyNamesOfUser;

import com.recipegen.backend.GetAllAllergies.Allergy;
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
 * the username. Allergy set is provided for many-to-many relationship table.
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "users")
public class UserNameAllergies {
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
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ailments",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "allergy_id")
    )
    private Set<Allergy> allergies = new HashSet<>();
    
    protected UserNameAllergies() {}
    
    public UserNameAllergies(String name, Set<Allergy> allergies) {
        setName(name);
        this.allergies = allergies;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()
                || name.length() > 64) {
            throw new IllegalArgumentException("Invalid name!");
        }

        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Set<Allergy> getAllergies() {
        return this.allergies;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        
        if (!(o instanceof UserNameAllergies other)) {
            return false;
        }
        
        return Objects.equals(this.id, other.id);
    }
    
    @Override
    public int hashCode() {
        return this.id == null ? 0 : this.id.hashCode();
    }
}
