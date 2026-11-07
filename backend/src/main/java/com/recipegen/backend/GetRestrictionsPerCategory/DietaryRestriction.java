package com.recipegen.backend.GetRestrictionsPerCategory;

import com.fasterxml.uuid.Generators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@SuppressWarnings("unused")
@Entity
@Table(name = "dietary_restrictions")
public class DietaryRestriction {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", columnDefinition = "CHAR(36)",
            unique = true, nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "name", unique = true,
            nullable = false)
    @Size(max = 32)
    private String name;

    @NotNull
    @Column(name = "category",
            nullable = false)
    @Size(max = 16)
    private String category;
    private static final String[] allowedCategories =
            {"Personal", "Religious"};

    protected DietaryRestriction() {}

    public DietaryRestriction(String name, String category) {
        this.setName(name.trim());
        this.setCategory(category);

        this.id = Generators.timeBasedEpochGenerator().generate();
    }

    public void setName(String name) {
        if (this.name.isBlank() || this.name.length() > 32) {
            throw new IllegalArgumentException("Invalid name!");
        }
        this.name = name;
    }

    public void setCategory(String category) {
        if(!(Arrays.asList(allowedCategories).contains(category))) {
            throw new IllegalArgumentException("Invalid category");
        }
        this.category = category;
    }

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DietaryRestriction other)) {
            return false;
        }

        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return this.id == null ? 0 : this.id.hashCode();
    }
}
