package com.projectnt.security.auth;

import com.projectnt.loan.LoanEntity;
import com.projectnt.other.common_types.UserRole;
import com.projectnt.user.error.UserDoesntExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

    public static AuthInfo getAuthInfo(Authentication authentication) {
        if(authentication==null || !authentication.isAuthenticated()){
            return null;
        }

        UserRole role = null;
        var username= authentication.getName();
        var authorities= authentication.getAuthorities().stream().toList();
        if(!authorities.isEmpty() && authentication.isAuthenticated()){
            role = UserRole.valueOf(authorities.getFirst().getAuthority());
        }
        return new AuthInfo(role, username);
    }

    public record AuthInfo(UserRole role, String username) {
    }

    public boolean isOwnerOrAdmin(AuthInfo auth, Long userId) {
        if(userId==null || auth==null){
            return false;
        }
        return auth.role() != null && auth.role().equals(UserRole.ROLE_ADMIN) || isOwner(auth.username(), userId);
    }
}
