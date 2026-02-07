package com.example.service;

import com.example.database.repository.UserRepository;
import com.example.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Setter
@ToString
public class UserService {
    private UserMapper userMapper;
    private UserRepository userRepository;
}
