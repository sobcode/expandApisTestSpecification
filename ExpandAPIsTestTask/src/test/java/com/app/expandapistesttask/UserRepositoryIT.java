package com.app.expandapistesttask;

import com.app.expandapistesttask.model.User;
import com.app.expandapistesttask.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class UserRepositoryIT {
    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void cleanData(){
        userRepository.deleteAll();
    }

    @Test
    public void whenSaveAndRetrieveEntity_thenOK() {
        User afterSaveUser = userRepository.save(new User("Pavlo", "PavLO1"));
        User retrievedUser = userRepository.findById(afterSaveUser.getId()).get();

        Assertions.assertNotEquals(0, afterSaveUser.getId());
        Assertions.assertNotNull(retrievedUser);
        Assertions.assertEquals(afterSaveUser.getUsername(), retrievedUser.getUsername());
    }

    @Test
    public void retrieveEntityByUsername() {
        User afterSaveUser = userRepository.save(new User("Pavlo", "PavLO1"));
        User retrievedUser = userRepository.findUserByUsername(afterSaveUser.getUsername());

        Assertions.assertNotNull(retrievedUser);
        Assertions.assertEquals(afterSaveUser.getUsername(), retrievedUser.getUsername());
    }

}
