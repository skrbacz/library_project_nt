package com.projectnt.user.dto;

public class GetUserDto {

    private long userId;
    private String lastName;
    private String email;
    private String name;

    public GetUserDto(long userId, String lastName, String email, String name) {
        this.userId = userId;
        this.lastName = lastName;
        this.email = email;
        this.name = name;
    }

    public GetUserDto() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
