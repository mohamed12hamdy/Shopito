package com.example.Shopito.Dtos.User;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequestDto {

    @NotBlank(message = "please,Enter your old password")
    private String oldPassword;
    @NotBlank(message = "Password can't be empty,please enter a password")
    private String newPassword;

}
