package com.projectnt.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CreateUserDto {

    @NotBlank(message = "Last name is required")
    @Schema(name="lastName", example = "last name")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Schema(name="email", example = "example@email.com")
    @Email
    private String email;

    @NotBlank(message = "Name is required")
    @Schema(name="name", example = "name")
    private String name;

    public CreateUserDto(String username, String email, String name) {
        this.lastName = username;
        this.email = email;
        this.name = name;
    }

    public CreateUserDto() {
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
}
