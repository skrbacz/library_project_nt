package com.projectnt.security.auth;

import com.projectnt.security.login_register_dto.LoginDto;
import com.projectnt.security.login_register_dto.LoginResponseDto;
import com.projectnt.security.login_register_dto.RegisterDto;
import com.projectnt.security.login_register_dto.RegisterResponseDto;
import com.projectnt.security.error.AuthError;
import com.projectnt.user.error.UserAlreadyExists;
import com.projectnt.security.jwt.JwtService;
import com.projectnt.user.UserEntity;
import com.projectnt.user.UserRepository;
import com.projectnt.user.error.UserDoesntExist;
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
        Optional<UserEntity> existingEmail= userRepository.findByEmail(dto.getEmail());

        if (existingAuth.isPresent()){
            throw UserAlreadyExists.createWithUsername(dto.getUsername());
        }else if (existingEmail.isPresent()){
            throw UserAlreadyExists.createWithEmail(dto.getEmail());
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
        AuthEntity authEntity=authRepository.findByUsername(dto.getUsername()).orElseThrow(()-> UserDoesntExist.createWithUsername(dto.getUsername()));
        if(!passwordEncoder.matches(dto.getPassword(),authEntity.getPassword())){
            throw AuthError.createPass();
        }
       String token=  jwtService.generateToken(authEntity);

       return new LoginResponseDto(token);
    }
}
