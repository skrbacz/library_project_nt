package com.projectnt.user;

import com.projectnt.review.dto.CreateReviewDto;
import com.projectnt.user.dto.CreateUserDto;
import com.projectnt.user.dto.CreateUserResponseDto;
import com.projectnt.user.dto.GetUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<GetUserDto> getAllUers(){return userService.getAll();}

    @GetMapping("/{user_id}")
    public GetUserDto getOne(@PathVariable long user_id){return userService.getOne(user_id);}

    @PostMapping
    public ResponseEntity<CreateUserResponseDto> create(@RequestBody CreateUserDto user){
        var newUser= userService.create(user);
        return new ResponseEntity<>(newUser,HttpStatus.CREATED);
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> delete(@PathVariable long user_id){
        userService.delete(user_id);
        return ResponseEntity.noContent().build();
    }
}
