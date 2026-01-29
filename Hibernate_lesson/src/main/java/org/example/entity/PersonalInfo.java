package org.example.entity;

import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.converter.BirthdayConverter;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class PersonalInfo{
//    @Serial
//    private static final long serialVersionUID = -4148019503802061618L;

    private String firstName;
    private String lastName;
    @Convert(converter = BirthdayConverter.class)
    private Birthday birthday;
}
