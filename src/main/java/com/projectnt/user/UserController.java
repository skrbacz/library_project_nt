package com.projectnt.user;

import com.projectnt.user.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<GetUserPagesDto> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        GetUserPagesDto dto = userService.getAll(page,size);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<GetUserDto> getOne(@PathVariable long user_id){
        GetUserDto dto= userService.getOneById(user_id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<GetUserDto> getMe(Principal principal){
        String username= principal.getName();
        GetUserDto dto = userService.getUserByUsername(username);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PatchMapping("/{user_id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PatchUserResponseDto> update(@PathVariable long user_id, @RequestBody PatchUserDto dto){
        PatchUserResponseDto responseDto= userService.update(user_id, dto);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateUserResponseDto> create(@RequestBody @Validated CreateUserDto user){
        var newUser= userService.create(user);
        return new ResponseEntity<>(newUser,HttpStatus.CREATED);
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> delete(@PathVariable long user_id){
        userService.delete(user_id);
        return ResponseEntity.noContent().build();
    }

}
