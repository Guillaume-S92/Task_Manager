package com.portfolio.TaskManager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.portfolio.TaskManager.dto.UserDto;
import com.portfolio.TaskManager.model.User;
import com.portfolio.TaskManager.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(UserDto userDto) {
        userDto.setRole("USER");

        // Validate password length before encoding
        if (userDto.getPassword().length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters");
        }

        // Encode password and save user
        User user = new User(userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()), userDto.getRole(),
                userDto.getFullname());
        return userRepository.save(user);
    }

}
