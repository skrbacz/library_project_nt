package com.projectnt.register_login.dto;

import com.projectnt.common_types.UserRole;

public class RegisterResponseDto {
    private String username;

    private long userId;
    private UserRole role;

    public RegisterResponseDto(String username, long userId, UserRole role) {
        this.username = username;
        this.userId = userId;
        this.role = role;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
