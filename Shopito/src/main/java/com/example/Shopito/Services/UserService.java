package com.example.Shopito.Services;

import com.example.Shopito.Dtos.Login.LoginRequestDto;
import com.example.Shopito.Dtos.UserManagment.UserManagementRequestDto;
import com.example.Shopito.Dtos.UserManagment.UserManagementResponseDto;
import com.example.Shopito.Dtos.User.UserRegisterDto;
import com.example.Shopito.Dtos.User.UserResponseDto;
import com.example.Shopito.Entities.users;
import com.example.Shopito.Exceptions.UserAlreadyExists;
import com.example.Shopito.Exceptions.UserNotFoundException;
import com.example.Shopito.Repositories.UserRepository;
import com.example.Shopito.Security.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
     @Autowired
    private UserRepository userRepository;

     @Autowired
     private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public users registerUser(UserRegisterDto dto){
        if(userRepository.existsByEmail(dto.getEmail())){
           throw  new UserAlreadyExists("This email is already registered");
        }

         users user = new users();
         user.setUsername(dto.getUsername());
         user.setEmail(dto.getEmail());
         user.setPassword(passwordEncoder.encode(dto.getPassword()));
         return userRepository.save(user);
    }
    public UserResponseDto  getUserById(int id){
        UserResponseDto userResponseDto = new UserResponseDto();
        users user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        userResponseDto.setId(user.getId());
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setRole(user.getRole());
        return userResponseDto;

    }

    public String loginUser(LoginRequestDto request) {
        users user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtService.generateToken(user);
    }

    public UserManagementResponseDto GetCurrentUserDetails(int userId){
        users user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        UserManagementResponseDto dto = new UserManagementResponseDto();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        return dto;
    }
    @Transactional
    public void updateUserProfile(Integer userId, UserManagementRequestDto dto){

        users user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());

        userRepository.save(user);

    }


}
