package com.app.expandapistesttask.service;

import com.app.expandapistesttask.model.User;
import com.app.expandapistesttask.model.dto.UserDTO;

public interface UserService {
    User findUserByUsername(String username);

    void addUser(UserDTO userDTO);
}
