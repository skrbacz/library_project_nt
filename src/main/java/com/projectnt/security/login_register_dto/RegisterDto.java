package com.projectnt.security.login_register_dto;

import com.projectnt.other.common_types.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RegisterDto {

    @NotBlank(message = "Password is required")
    @Schema(name="password", example = "password")
    private String password;

    @NotBlank(message = "Username is required")
    @Schema(name="username", example = "user")
    private String username;

    @NotNull(message = "Role is required")
    @Schema(name="role", example = "ROLE_READER")
    private UserRole role;

    @NotBlank(message = "Email is required")
    @Schema(name= "email", example = "example@email.com")
    @Email
    private String email;

    public RegisterDto(String password, String username, UserRole role, String email) {
        this.password = password;
        this.username = username;
        this.role = role;
        this.email = email;
    }

    public RegisterDto() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
