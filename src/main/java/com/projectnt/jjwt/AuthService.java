package com.projectnt.jjwt;

import com.projectnt.common_types.UserRole;
import com.projectnt.register_login.dto.LoginDto;
import com.projectnt.register_login.dto.LoginResponseDto;
import com.projectnt.register_login.dto.RegisterDto;
import com.projectnt.register_login.dto.RegisterResponseDto;
import com.projectnt.user.UserEntity;
import com.projectnt.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;

    private final JwtService jwtService;

    @Autowired
    public AuthService(AuthRepository authRepository, UserRepository userRepository, JwtService jwtService) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public RegisterResponseDto register(RegisterDto dto){
        UserEntity userEntity= new UserEntity();
        userEntity.setEmail(dto.getEmail());
        UserEntity createdUser= userRepository.save(userEntity);

        AuthEntity authEntity= new AuthEntity();
        authEntity.setPassword(dto.getPassword());
        authEntity.setUsername(dto.getUsername());
        authEntity.setRole(dto.getRole());
        authEntity.setUser(createdUser);

        AuthEntity createdAuth= authRepository.save(authEntity);

        return new RegisterResponseDto(createdAuth.getUsername(),createdAuth.getRole());
    }

    public LoginResponseDto login(LoginDto dto){
        AuthEntity authEntity=authRepository.findByUsername(dto.getUsername()).orElseThrow(RuntimeException::new);
        if(!authEntity.getPassword().equals(dto.getPassword())){
            throw new RuntimeException("Password or username doesn't match");
        }
       String token=  jwtService.generateToken(authEntity);

       return new LoginResponseDto(token);
    }
}
