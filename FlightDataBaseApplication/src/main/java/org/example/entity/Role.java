package org.example.entity;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;

public enum Role {
    USER, ADMIN;

    public static Optional<Role> getRole(String role) {
        return Arrays.stream(Role.values()).filter(r -> r.name().equals(role)).findFirst();
    }
}
