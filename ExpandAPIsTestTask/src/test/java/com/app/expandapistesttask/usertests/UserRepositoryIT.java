package com.app.expandapistesttask.usertests;

import com.app.expandapistesttask.model.User;
import com.app.expandapistesttask.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryIT {
    @Autowired
    private UserRepository userRepository;

    String username = "Pavlo";
    String password = "PavlO1";

    @AfterEach
    public void cleanData(){
        userRepository.deleteAll();
    }

    @Test
    public void whenSaveAndRetrieveEntity_thenOK() {
        User afterSaveUser = saveUser(username, password);
        User retrievedUser = userRepository.findById(afterSaveUser.getId()).get();

        Assertions.assertNotEquals(0, afterSaveUser.getId());
        Assertions.assertNotNull(retrievedUser);
        Assertions.assertEquals(username, retrievedUser.getUsername());
    }

    @Test
    public void retrieveEntityByUsername() {
        User afterSaveUser = saveUser(username, password);
        User retrievedUser = userRepository.findUserByUsername(afterSaveUser.getUsername());

        Assertions.assertNotNull(retrievedUser);
        Assertions.assertEquals(username, retrievedUser.getUsername());
    }

    private User saveUser(String username, String password) {
        return userRepository.save(new User(username, password));
    }

}
