package com.projectnt.security.auth;

import com.projectnt.others.login_register_dto.LoginDto;
import com.projectnt.others.login_register_dto.LoginResponseDto;
import com.projectnt.others.login_register_dto.RegisterDto;
import com.projectnt.others.login_register_dto.RegisterResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@PreAuthorize("hasRole('ADMIN')")
public class AuthController {
    @Autowired
    public AuthController(AuthService authService){
        this.authService= authService;
    }
    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@Validated @RequestBody RegisterDto requestBody){
        RegisterResponseDto dto = authService.register(requestBody);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<LoginResponseDto> login(@Validated @RequestBody LoginDto requestBody){
       LoginResponseDto dto= authService.login(requestBody);
       return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
