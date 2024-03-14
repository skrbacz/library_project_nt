package com.projectnt.jjwt;

import com.projectnt.user.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class authController {
    public void singIn(){

    }

//    @GetMapping("/{user_id}")
//    public ResponseEntity<UserEntity> signUp(@RequestBody UserEntity user){
//        var newUser= userService.create(user);
//        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
//    }

    public void signUp(){

    }
}
