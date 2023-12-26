package com.app.expandapistesttask.usertests;

import com.app.expandapistesttask.config.jwt.JwtTokenProvider;
import com.app.expandapistesttask.model.dto.AuthenticateRequestDTO;
import com.app.expandapistesttask.model.dto.AuthenticationResponseDTO;
import com.app.expandapistesttask.model.dto.UserDTO;
import com.app.expandapistesttask.repository.UserRepository;
import com.app.expandapistesttask.service.AuthenticationService;
import com.app.expandapistesttask.service.ProductService;
import com.app.expandapistesttask.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthenticationServiceIT {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @AfterEach
    public void cleanData(){
        userRepository.deleteAll();
    }

    @Test
    public void whenAuthenticateUser_thenReceiveProperToken() {
        String username = "Pavlo";
        String password = "PavlO1";

        userService.addUser(new UserDTO(username, password));
        AuthenticationResponseDTO responseDTO = authenticationService.authenticateUser(
                new AuthenticateRequestDTO(username, password));

        Assertions.assertTrue(jwtTokenProvider.validateToken(responseDTO.getToken()));
    }
}
