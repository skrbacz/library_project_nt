package com.projectnt.security.auth;

import com.projectnt.user.error.UserDoesntExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class OwnershipService {
    protected final AuthRepository authRepository;


    @Autowired
    public OwnershipService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }



    public boolean isOwner(String username, Long userId){

        if (userId== null || username == null){
            return false;
        }

        AuthEntity authEntity= authRepository.findByUsername(username).orElseThrow(()-> UserDoesntExist.createWithUsername(username));
        return  userId== authEntity.getUser().getUserId();

    }
}
