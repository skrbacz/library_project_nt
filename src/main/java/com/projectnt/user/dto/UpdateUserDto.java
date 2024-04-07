package com.projectnt.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.openapitools.jackson.nullable.JsonNullable;

public class UpdateUserDto {

    @Schema(name = "email", example = "example@email.com", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private JsonNullable<String> email;

    @Schema(name = "name", example = "name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private JsonNullable<String> name;

    @Schema(name = "lastName", example = "last name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private JsonNullable<String> lastname;

    public UpdateUserDto(JsonNullable<String> email, JsonNullable<String> name, JsonNullable<String> lastname) {
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
