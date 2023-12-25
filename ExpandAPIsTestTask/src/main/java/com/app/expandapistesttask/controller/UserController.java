package com.app.expandapistesttask.controller;

import com.app.expandapistesttask.model.dto.AuthenticateRequestDTO;
import com.app.expandapistesttask.model.dto.AuthenticationResponseDTO;
import com.app.expandapistesttask.model.dto.UserDTO;
import com.app.expandapistesttask.service.AuthenticationService;
import com.app.expandapistesttask.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Autowired
    public UserController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/add")
    public void addUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody AuthenticateRequestDTO authenticateRequestDTO) {
        return ResponseEntity.ok(authenticationService.authenticateUser(authenticateRequestDTO));
    }
}
