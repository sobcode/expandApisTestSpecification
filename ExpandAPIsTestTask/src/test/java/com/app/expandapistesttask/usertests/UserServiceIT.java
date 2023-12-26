package com.app.expandapistesttask.usertests;

import com.app.expandapistesttask.model.User;
import com.app.expandapistesttask.model.dto.UserDTO;
import com.app.expandapistesttask.repository.UserRepository;
import com.app.expandapistesttask.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class UserServiceIT {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private String username = "Pavlo";
    private String password = "PavlO1";

    @AfterEach
    public void cleanData(){
        userRepository.deleteAll();
    }

    @Test
    public void whenAddUserAndRetrieveByUsername_thenOK() {
        User afterSaveUser = addUser(username, password);
        User retrievedUser = userService.findUserByUsername(afterSaveUser.getUsername());

        Assertions.assertNotNull(retrievedUser);
        Assertions.assertEquals(username, retrievedUser.getUsername());
    }

    @Test
    public void whenAddUser_thenPasswordIsEncrypted() {
        User afterSaveUser = addUser(username, password);
        User retrievedUser = userService.findUserByUsername(afterSaveUser.getUsername());

        Assertions.assertTrue(passwordEncoder.matches(password, retrievedUser.getPassword()));
    }

    private User addUser(String username, String password) {
        return userService.addUser(new UserDTO(username, password));
    }
}
