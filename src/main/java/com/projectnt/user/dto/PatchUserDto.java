package com.projectnt.user.dto;

import org.openapitools.jackson.nullable.JsonNullable;

public class PatchUserDto {


    private JsonNullable<String> email;
    private JsonNullable<String> name;
    private JsonNullable<String> lastname;

    public PatchUserDto(JsonNullable<String> email, JsonNullable<String> name, JsonNullable<String> lastname) {
        this.email = email;
        this.name = name;
        this.lastname = lastname;
    }

    public JsonNullable<String> getEmail() {
        return email;
    }

    public void setEmail(JsonNullable<String> email) {
        this.email = email;
    }

    public JsonNullable<String> getName() {
        return name;
    }

    public void setName(JsonNullable<String> name) {
        this.name = name;
    }

    public JsonNullable<String> getLastname() {
        return lastname;
    }

    public void setLastname(JsonNullable<String> lastname) {
        this.lastname = lastname;
    }
}
