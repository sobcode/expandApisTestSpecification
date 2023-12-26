package com.app.expandapistesttask.service.impl;

import com.app.expandapistesttask.exception.AuthenticationException;
import com.app.expandapistesttask.model.User;
import com.app.expandapistesttask.model.dto.UserDTO;
import com.app.expandapistesttask.repository.UserRepository;
import com.app.expandapistesttask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public void addUser(UserDTO userDTO) {
        if(userRepository.findUserByUsername(userDTO.getUsername()) != null) {
            throw new AuthenticationException("User with username " + userDTO.getUsername() + " is already exists!");
        }

        User user = new User(userDTO.getUsername(),
                passwordEncoder.encode(userDTO.getPassword()));

        userRepository.save(user);
    }
}
