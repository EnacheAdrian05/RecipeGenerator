package com.recipegen.backend.Login;

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
 * A user has a unique combination of name and password.
 * Knowing this, it is possible to retrieve a single user.
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "users")
public class LoginUser {
    @Id
    @NotNull
    @Column(name = "id",
            unique = true, nullable = false)
    private UUID id;

    @Column(name = "name",
            unique = true, nullable = false)
    @NotNull
    @Size(max = 64)
    private String name;

    @Column(name = "password",
            nullable = false)
    @NotNull
    @Size(min = 8, max = 24)
    private String password;

    protected LoginUser() {}

    public LoginUser(String name, String password) {
        if (name == null || name.isBlank()
            || name.length() > 64) {
            throw new IllegalArgumentException("Invalid name!");
        }

        if (password == null || password.isBlank()
            || password.length() < 8 ||
                password.length() > 24) {
            throw new IllegalArgumentException("Invalid password!");
        }

        this.id = Generators.timeBasedEpochGenerator().generate();
        this.name = name.trim();
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
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
