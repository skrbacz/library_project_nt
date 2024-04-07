package com.projectnt.user;

import com.projectnt.loan.LoanEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="users", schema = "library")
public class UserEntity {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    @Column(name = "userId")
    private long userId;

    @Basic
    @Column(name="email",nullable = false)
    private String email;

    @Basic
    @Column(name="name")
    private String name;

    @Basic
    @Column(name="lastName")
    private String lastName;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<LoanEntity> loans;

    public List<LoanEntity> getLoans() {
        return loans;
    }

    public void setLoans(List<LoanEntity> loans) {
        this.loans = loans;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String username) {
        this.lastName = username;
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
