package com.example.Shopito.Dtos.Login;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password can't be  empty,please enter your password")
    private String password;


}
