package org.example.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.example.dto.UserDto;
import org.example.entity.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper implements Mapper<UserDto, User> {
    private static final UserMapper INSTANCE = new UserMapper();

    @Override
    public static UserDto mapFrom(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .birthday(user.getBirthday())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .gender(user.getGender())
                .build();
    }

    public static UserMapper getINSTANCE() {
        return INSTANCE;
    }
}
