package com.app.expandapistesttask.service.impl;

import com.app.expandapistesttask.config.jwt.JwtTokenProvider;
import com.app.expandapistesttask.model.User;
import com.app.expandapistesttask.model.dto.AuthenticateRequestDTO;
import com.app.expandapistesttask.model.dto.AuthenticationResponseDTO;
import com.app.expandapistesttask.repository.UserRepository;
import com.app.expandapistesttask.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository,
                           JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }
    @Override
    public AuthenticationResponseDTO authenticateUser(AuthenticateRequestDTO authenticateRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticateRequestDTO.getUsername(),
                        authenticateRequestDTO.getPassword()));

        User user = userRepository.findUserByUsername(authenticateRequestDTO.getUsername());

        if(user == null){
            throw new NoSuchElementException("User with username " + authenticateRequestDTO.getUsername() +
                    " not found!");
        }

        String token = jwtTokenProvider.createToken(user.getUsername(), "Admin");

        return new AuthenticationResponseDTO(token, user.getUsername());
    }
}
