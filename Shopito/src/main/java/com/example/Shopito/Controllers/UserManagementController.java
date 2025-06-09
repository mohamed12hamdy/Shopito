package com.example.Shopito.Controllers;

import com.example.Shopito.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserManagementController {

    @Autowired
    private UserService service;

    @GetMapping("/users/profile")
    public ResponseEntity<?>GetCurrentUserDetails(HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("userId");


        return  ResponseEntity.ok(service.GetCurrentUserDetails(userId));

    }



}
