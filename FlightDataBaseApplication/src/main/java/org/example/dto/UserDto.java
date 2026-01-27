package org.example.dto;

import lombok.Builder;
import lombok.Data;
import org.example.entity.Gender;
import org.example.entity.Role;

import java.time.LocalDate;

@Data
@Builder
public class UserDto {
    Integer id;
    String name;
    LocalDate birthday;
    String email;
    String password;
    Role role;
    Gender gender;
}
