package org.example.entity;

import java.util.Arrays;
import java.util.Optional;

public enum Gender{
    MALE,FEMALE;

    public static Optional<Gender> getGender(String gender){
        return Arrays.stream(Gender.values()).filter(g -> g.name().equals(gender)).findFirst();
    }
}
