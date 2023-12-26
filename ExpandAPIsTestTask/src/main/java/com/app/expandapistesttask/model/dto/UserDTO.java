package com.app.expandapistesttask.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class UserDTO {
    @NonNull
    private String username;
    @NonNull
    private String password;
}
