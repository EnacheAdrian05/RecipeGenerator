package com.recipegen.backend.Login;

import com.fasterxml.uuid.Generators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Objects;
import java.util.UUID;

/**
 * A user has a unique combination of name and password.
 * Knowing this, it is possible to retrieve a single user.
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "users")
public class LoginUser {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", columnDefinition = "CHAR(36)",
            unique = true, nullable = false)
    private UUID id;

    @Column(name = "name",
            unique = true, nullable = false)
    @NotNull
    @Size(max = 64)
    private String name;

    @Column(name = "password_hash",
            nullable = false)
    @NotNull
    @Size(min = 60, max = 255)
    private String passwordHash;

    protected LoginUser() {}

    public LoginUser(String name, String passwordHash) {
        this.setName(name.trim());
        this.setPasswordHash(passwordHash);
        this.id = Generators.timeBasedEpochGenerator().generate();
        this.passwordHash = passwordHash;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()
                || name.length() > 64) {
            throw new IllegalArgumentException("Invalid name!");
        }

        this.name = name;
    }

    public void setPasswordHash(String passwordHash) {
        if (passwordHash.length() < 60 ||
                passwordHash.length() > 255) {
            throw new IllegalArgumentException("Invalid hash!");
        }
        this.passwordHash = passwordHash;
    }

    public String getName() {
        return this.name;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof LoginUser other)) {
            return false;
        }

        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return this.id == null ? 0 : this.id.hashCode();
    }
}
