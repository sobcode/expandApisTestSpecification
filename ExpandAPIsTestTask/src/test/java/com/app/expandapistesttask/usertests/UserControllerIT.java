package com.app.expandapistesttask.usertests;

import com.app.expandapistesttask.config.jwt.JwtTokenProvider;
import com.app.expandapistesttask.controller.UserController;
import com.app.expandapistesttask.model.User;
import com.app.expandapistesttask.model.dto.AuthenticateRequestDTO;
import com.app.expandapistesttask.model.dto.AuthenticationResponseDTO;
import com.app.expandapistesttask.model.dto.UserDTO;
import com.app.expandapistesttask.repository.UserRepository;
import com.app.expandapistesttask.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class UserControllerIT {
    @Autowired
    private UserController userController;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private String username = "Pavlo";
    private String password = "PavlO1";

    @AfterEach
    public void cleanData(){
        userRepository.deleteAll();
    }

    @Test
    public void whenAddUser_thenItIsAddedToDatabase() {
        userController.addUser(new UserDTO(username, password));
        User user = userRepository.findUserByUsername(username);

        Assertions.assertEquals(username, user.getUsername());
    }

    @Test
    public void whenAuthenticate_thenReceiveProperJWT() {
        userService.addUser(new UserDTO(username, password));
        ResponseEntity<AuthenticationResponseDTO> responseEntity =
                userController.authenticate(new AuthenticateRequestDTO(username, password));
        String token = responseEntity.getBody().getToken();

        Assertions.assertTrue(jwtTokenProvider.validateToken(token));
    }
}
