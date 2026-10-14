package com.self.taskmanager.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.self.taskmanager.dto.LoginRequestDto;
import com.self.taskmanager.dto.LoginResponseDto;
import com.self.taskmanager.dto.RegisterRequestDto;
import com.self.taskmanager.dto.UserResponseDto;
import com.self.taskmanager.exception.EmailAlreadyExistsException;
import com.self.taskmanager.model.Role;
import com.self.taskmanager.model.User;
import com.self.taskmanager.repository.UserRepository;
import com.self.taskmanager.security.JwtService;

@Service 
public class AuthService {
      private final UserRepository userRepository;
      private final PasswordEncoder passwordEncoder;
      private final AuthenticationManager authenticationManager;
      private final JwtService jwtService;

      public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
          this.userRepository = userRepository;
          this.passwordEncoder = passwordEncoder;
          this.authenticationManager = authenticationManager;
          this.jwtService = jwtService;
      }

      public UserResponseDto register(RegisterRequestDto dto) {
        if(userRepository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists: "+ dto.getEmail());
        }else{

          User user = new User();

          user.setName(dto.getName());
          user.setEmail(dto.getEmail());
          user.setPassword(passwordEncoder.encode(dto.getPassword()));
          user.setRole(Role.USER);

          User savedUser = userRepository.save(user);

          UserResponseDto response = new UserResponseDto();

          response.setId(savedUser.getId());
          response.setName(savedUser.getName());
          response.setEmail(savedUser.getEmail());
          response.setRole(savedUser.getRole());

          return response;
        }
      }

      public LoginResponseDto login(LoginRequestDto dto) {
        authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );
        String token = jwtService.generateToken(dto.getEmail());

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        LoginResponseDto response = new LoginResponseDto();

        response.setToken(token);
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());

        return response;
      }

    
}
