package com.example.Shopito.Controllers;

import com.example.Shopito.Dtos.Login.LoginRequestDto;
import com.example.Shopito.Dtos.User.UserRegisterDto;
import com.example.Shopito.Dtos.User.UserResponseDto;
import com.example.Shopito.Entities.users;
import com.example.Shopito.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(
            summary = "Register a New User",
            description = """
        This endpoint allows a new user to register by providing a unique username, a valid email address, and a password.

        📝 Validation is applied on all fields.
        ⚠️ Email and username must be unique.
        🔐 Password is stored securely using encryption.
    """
    )

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto>register( @Valid @RequestBody UserRegisterDto userDto){
        users user = service.registerUser(userDto);
        UserResponseDto responseDTO = service.getUserById(user.getId());
        return ResponseEntity.ok(responseDTO);
    }
    @Operation(
            summary = "User Login",
            description = """
        Authenticates a user using their email and password.

        ✅ If the credentials are valid, a JWT token is returned for authenticated access.
        ❌ If invalid, an error message will be returned.

        🔐 This endpoint does not require authentication (public).
    """
    )

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDto request) {
        String token = service.loginUser(request);
        return ResponseEntity.ok(token);
    }


}
