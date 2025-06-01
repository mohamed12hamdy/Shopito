package com.example.Shopito.Controllers;

import com.example.Shopito.Dtos.UserRegisterDto;
import com.example.Shopito.Dtos.UserResponseDto;
import com.example.Shopito.Entities.users;
import com.example.Shopito.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Auth")
public class AuthController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto>register( @Valid @RequestBody UserRegisterDto userDto){
        users user = service.registerUser(userDto);
        UserResponseDto responseDTO = service.getUserById(user.getId());
        return ResponseEntity.ok(responseDTO);
    }






}
