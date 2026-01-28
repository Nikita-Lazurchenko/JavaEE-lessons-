package org.example.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record Birthday(LocalDate birthday) {
    public Integer getAge(LocalDate birthday) {
        return Math.toIntExact(ChronoUnit.YEARS.between(birthday, LocalDate.now()));
    }
}
