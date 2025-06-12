package com.example.Shopito.Controllers;

import com.example.Shopito.Dtos.UserManagment.UserManagementRequestDto;
import com.example.Shopito.Entities.users;
import com.example.Shopito.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserManagementController {

    @Autowired
    private UserService service;

    @Operation(
            summary = "view user's data",
            description = "Get the Authenticated user's data like profile page."
    )
    @GetMapping("/users/profile")
    public ResponseEntity<?>GetCurrentUserDetails(HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("userId");


        return  ResponseEntity.ok(service.GetCurrentUserDetails(userId));

    }

    @Operation(
            summary = "update the user's data",
            description = "update the Authenticated user's data."
    )
    @PutMapping("/users/profile")
    public ResponseEntity<?>UpdateCurrentUserData(@RequestBody  UserManagementRequestDto dto, HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("userId");

        service.updateUserProfile(userId,dto);
        return ResponseEntity.ok("Profile updated successfully");

    }



}
