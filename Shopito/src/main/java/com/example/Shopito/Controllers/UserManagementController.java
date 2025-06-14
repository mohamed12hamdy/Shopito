package com.example.Shopito.Controllers;

import com.example.Shopito.Dtos.User.ChangePasswordRequestDto;
import com.example.Shopito.Dtos.UserManagment.UserManagementRequestDto;
import com.example.Shopito.Entities.users;
import com.example.Shopito.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserManagementController {

    @Autowired
    private UserService service;

    @Operation(
            summary = "View User's Profile",
            description = """
        This endpoint returns the authenticated user's data, similar to a profile page.

        🔐 Requires a valid JWT or active session.
        📦 Returns user's ID, name, email, and other public profile details.
    """
    )

    @GetMapping("/users/profile")
    public ResponseEntity<?>GetCurrentUserDetails(HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("userId");


        return  ResponseEntity.ok(service.GetCurrentUserDetails(userId));

    }

    @Operation(
            summary = "Update User's Profile",
            description = """
        This endpoint allows the authenticated user to update their personal data (e.g., name, email, etc.).
        
        🔐 Requires authentication (JWT or session).
        🛠️ Only editable fields will be updated.
        ❗Sensitive fields like password should be updated using the change-password endpoint.
    """
    )

    @PutMapping("/users/profile")
    public ResponseEntity<?>UpdateCurrentUserData(@RequestBody  UserManagementRequestDto dto, HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("userId");

        service.updateUserProfile(userId,dto);
        return ResponseEntity.ok("Profile updated successfully");

    }
    @Operation(
            summary = "Change Password",
            description = """
        This endpoint allows the authenticated user to change their password.
        
        🔒 It requires the old password for verification.
        ✅ After a successful password change, the user will be logged out (token/session invalidated).
    """
    )

    @PutMapping("/users/change-password")
    public ResponseEntity<?>ChangePassword(@RequestBody ChangePasswordRequestDto requestDto, @AuthenticationPrincipal users user){
        service.ChangePassword(requestDto,user);
        return ResponseEntity.ok("Password-Changed Successfully");
    }




}
