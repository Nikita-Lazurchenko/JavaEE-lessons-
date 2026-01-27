package org.example.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.example.dto.CreateUserDto;
import org.example.entity.Gender;
import org.example.entity.Role;
import org.example.utils.LocalDateFormater;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserValidator implements Validator<CreateUserDto> {
    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    @Override
    public ValidationResult isValid(CreateUserDto userDto){
        var validationResult = new ValidationResult();
        if(!LocalDateFormater.isValid(userDto.getBirthday())){
            validationResult.addError(Error.of("invalid.birthday","Birthday is incorrect/invalid"));
        }
        if(Role.getRole(userDto.getRole()).isEmpty()){
            validationResult.addError(Error.of("invalid.role","Role is invalid"));
        }
        if(Gender.getGender(userDto.getGender()).isEmpty()){
            validationResult.addError(Error.of("invalid.gender","Gender is invalid"));
        }

        return validationResult;
    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
