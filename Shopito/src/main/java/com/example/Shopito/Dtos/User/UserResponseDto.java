package com.example.Shopito.Dtos.User;

import com.example.Shopito.Entities.Enums.Role;
import lombok.Data;

@Data
public class UserResponseDto {

    private int id;
    private String username;
    private String email;
    private Role role;
}
