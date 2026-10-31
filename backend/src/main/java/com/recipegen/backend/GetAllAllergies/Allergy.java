package com.recipegen.backend.GetAllAllergies;

import com.fasterxml.uuid.Generators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;
import java.util.UUID;

/**
 * An allergy has its name and is identified
 * by a unique ID.
 */
@Entity
@Table(name = "allergies")
public class Allergy {
    @Id
    @NotNull
    @Column(name = "id",
            unique = true, nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "name", nullable = false)
    @Size(max = 128)
    private String name;

    protected Allergy() {}

    public Allergy(final String name) {
        this.id = Generators.timeBasedEpochGenerator().generate();
        if (name == null || name.isBlank() || name.length() > 128) {
            throw new IllegalArgumentException("Invalid name!");
        }

        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Allergy other)) {
            return false;
        }

        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return this.id == null ? 0 : this.id.hashCode();
    }
}
