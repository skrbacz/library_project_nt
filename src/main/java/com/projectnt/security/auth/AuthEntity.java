package com.projectnt.security.auth;


import com.projectnt.other.common_types.UserRole;
import com.projectnt.user.UserEntity;
import jakarta.persistence.*;

@Entity
@Table(name= "auth", schema = "library")
public class AuthEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long auth_id;

    @Column(name = "username", unique = true, nullable = false)
    @Basic
    private String username;

    @Column(name = "password", nullable = false)
    @Basic
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne
    @JoinColumn(name= "user_id",  nullable = false)
    private UserEntity user;

    public AuthEntity() {
    }

    public long getAuth_id() {
        return auth_id;
    }

    public void setAuth_id(long auth_id) {
        this.auth_id = auth_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
