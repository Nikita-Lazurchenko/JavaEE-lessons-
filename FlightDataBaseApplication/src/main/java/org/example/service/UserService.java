package org.example.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.example.dao.UserDao;
import org.example.dto.CreateUserDto;
import org.example.dto.UserDto;
import org.example.exception.ValidationException;
import org.example.mapper.CreateUserMapper;
import org.example.mapper.UserMapper;
import org.example.validator.CreateUserValidator;

import java.util.Optional;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final CreateUserMapper userMapper = CreateUserMapper.getINSTANCE();
    private final UserDao userDao = UserDao.INSTANCE;
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();


    public Integer createUser(CreateUserDto createUserDto) {
        var validationResult = createUserValidator.isValid(createUserDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var user = userMapper.mapFrom(createUserDto);
        userDao.save(user);

        return user.getId();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }

    public Optional<UserDto> login(String email, String password) {
        return userDao.findByEmailAndPassword(email, password).map(UserMapper::mapFrom);
    }
}
