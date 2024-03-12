package com.projectnt.user;

import jakarta.persistence.*;

@Entity
@Table(name="users", schema = "library")
public class UserEntity {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private long user_id;

    @Basic
    @Column(name="username")
    private String username;

    @Basic
    @Column(name="password")
    private String password;

    @Basic
    @Column(name="role")
    private String role;

    @Basic
    @Column(name="email")
    private String email;

    @Basic
    @Column(name="name")
    private String name;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
