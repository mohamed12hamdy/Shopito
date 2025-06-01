package com.example.Shopito.Dtos;

import com.example.Shopito.Entities.Role;
import lombok.Data;

@Data
public class UserResponseDto {

    private int id;
    private String username;
    private String email;
    private Role role;
}
