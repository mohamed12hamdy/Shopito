package com.example.Shopito.Dtos.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegisterDto {

    @NotBlank(message = "Username is required")
    private String username;

    @Email(message = "Email is not correct")
    @NotBlank(message = "You must Enter your email")

    private String email;

    @NotBlank(message = "Password can't be  empty,please enter your password")
    private String password;



}
