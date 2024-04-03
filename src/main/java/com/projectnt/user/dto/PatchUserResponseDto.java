package com.projectnt.user.dto;

public class PatchUserResponseDto {

    private long userId;
    private String email;
    private String name;
    private String lastname;

    public PatchUserResponseDto(long userId, String email, String name, String lastname) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}

