package com.projectnt.user;

import com.projectnt.user.dto.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ApiResponse(responseCode = "200")
    public ResponseEntity<UserPagesDto> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        UserPagesDto dto = userService.getAll(page,size);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "401", description = "User not found", content = @Content())
    })
    public ResponseEntity<UserDto> getOne(@PathVariable Long userId){
        UserDto dto= userService.getOneById(userId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Something went wrong", content = @Content())
    })
    public ResponseEntity<UserDto> getMe(Principal principal){
        String username= principal.getName();
        UserDto dto = userService.getUserByUsername(username);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PatchMapping("/{userId}")
    @PreAuthorize("isAuthenticated()")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update successful"),
            @ApiResponse(responseCode = "401", description = "Something went wrong", content = @Content())
    })
    public ResponseEntity<UpdateUserResponseDto> update(@PathVariable Long userId, @RequestBody UpdateUserDto dto){
        UpdateUserResponseDto responseDto= userService.update(userId, dto);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    @ApiResponse(responseCode = "200", description = "Deletion successful")
    public ResponseEntity<Void> delete(@PathVariable Long userId){
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }

}
