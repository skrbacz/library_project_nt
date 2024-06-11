package com.projectnt.user.dto;

public class UserDto {

    private Long userId;

    private String email;

    private String name;

    private String lastName;

    private String username;

    private String userRole;

    public UserDto(Long userId, String email, String name, String lastName, String username, String userRole) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.userRole = userRole;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
