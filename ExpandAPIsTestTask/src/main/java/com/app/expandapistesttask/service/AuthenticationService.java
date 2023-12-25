package com.app.expandapistesttask.service;

import com.app.expandapistesttask.model.dto.AuthenticateRequestDTO;
import com.app.expandapistesttask.model.dto.AuthenticationResponseDTO;

public interface AuthenticationService {
    AuthenticationResponseDTO authenticateUser(AuthenticateRequestDTO authenticateRequestDTO);
}
