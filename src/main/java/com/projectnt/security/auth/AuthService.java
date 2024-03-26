package com.projectnt.security.auth;

import com.projectnt.register_login.dto.LoginDto;
import com.projectnt.register_login.dto.LoginResponseDto;
import com.projectnt.register_login.dto.RegisterDto;
import com.projectnt.register_login.dto.RegisterResponseDto;
import com.projectnt.security.auth.error.UserAlreadyExists;
import com.projectnt.security.jwt.JwtService;
import com.projectnt.user.UserEntity;
import com.projectnt.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder, AuthRepository authRepository, UserRepository userRepository, JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Transactional
    public RegisterResponseDto register(RegisterDto dto){
        Optional<AuthEntity> existingAuth= authRepository.findByUsername(dto.getUsername());

        if (existingAuth.isPresent()){
            throw UserAlreadyExists.create(dto.getUsername());
        }

        UserEntity userEntity= new UserEntity();
        userEntity.setEmail(dto.getEmail());
        userRepository.save(userEntity);

        AuthEntity authEntity= new AuthEntity();
        String hashPassword= passwordEncoder.encode(dto.getPassword());
        authEntity.setPassword(hashPassword);
        authEntity.setUsername(dto.getUsername());
        authEntity.setRole(dto.getRole());
        authEntity.setUser(userEntity);
        authRepository.save(authEntity);

        return new RegisterResponseDto(authEntity.getUsername(), userEntity.getUserId(), authEntity.getRole());
    }

    public LoginResponseDto login(LoginDto dto){
        AuthEntity authEntity=authRepository.findByUsername(dto.getUsername()).orElseThrow(RuntimeException::new);
        if(!passwordEncoder.matches(dto.getPassword(),authEntity.getPassword())){
            throw new RuntimeException("Password or username don't match");
        }
       String token=  jwtService.generateToken(authEntity);

       return new LoginResponseDto(token);
    }
}
