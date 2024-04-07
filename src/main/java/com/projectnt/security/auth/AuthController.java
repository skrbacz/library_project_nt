package com.projectnt.security.auth;

import com.projectnt.security.login_register_dto.LoginDto;
import com.projectnt.security.login_register_dto.LoginResponseDto;
import com.projectnt.security.login_register_dto.RegisterDto;
import com.projectnt.security.login_register_dto.RegisterResponseDto;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Authorisation")
public class AuthController {
    @Autowired
    public AuthController(AuthService authService){
        this.authService= authService;
    }
    private final AuthService authService;

    @PostMapping("/register")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account successfully created"),
            @ApiResponse(responseCode = "401", description = "Something went wrong", content = @Content())
    })
    public ResponseEntity<RegisterResponseDto> register(@Validated @RequestBody RegisterDto requestBody){
        RegisterResponseDto dto = authService.register(requestBody);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    @SecurityRequirements
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login succeeded"),
            @ApiResponse(responseCode = "401", description = "Login failed", content = @Content())
    })
    public ResponseEntity<LoginResponseDto> login(@Validated @RequestBody LoginDto requestBody){
       LoginResponseDto dto= authService.login(requestBody);
       return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
