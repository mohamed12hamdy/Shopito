package com.example.Shopito.Services;

import com.example.Shopito.Dtos.UserRegisterDto;
import com.example.Shopito.Dtos.UserResponseDto;
import com.example.Shopito.Entities.users;
import com.example.Shopito.Exceptions.UserAlreadyExists;
import com.example.Shopito.Exceptions.UserNotFoundException;
import com.example.Shopito.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
     @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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


}
