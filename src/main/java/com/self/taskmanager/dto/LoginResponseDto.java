package com.self.taskmanager.dto;

import com.self.taskmanager.model.Role;

public class LoginResponseDto {
    private String token;
    private String email;
    private Role role;

    public LoginResponseDto() {
    }


    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    

    
}
