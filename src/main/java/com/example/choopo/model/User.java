package com.example.choopo.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "useres")
public class User {
    private long userId;

    private String username;

    private int userCode;

    private String password;

    private int userStatus;

    private String userType;

    private UserType userTypeId;

    private String passwordEncoder;

    public User(){

    }

    public User(String userType, String username, int userCode, String password, int userStatus, String passwordEncoder) {
        this.userType = userType;
        this.username = username;
        this.userCode = userCode;
        this.password = password;
        this.userStatus = userStatus;
        this.passwordEncoder = passwordEncoder;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "auto_user")
    @SequenceGenerator(name = "auto_user", sequenceName = "user_id")
    @Column(name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Column(name = "user_type", nullable = false)
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Column(name = "username", nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "user_code", nullable = false)
    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    @Column(name = "password", nullable = true)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "user_status", nullable = false)
    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    @Column(name = "password_encoder", nullable = true)
    public String getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(String passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.MERGE)
    @JoinColumn(name = "user_type_id", nullable = true)
    public UserType getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(UserType userTypeId) {
        this.userTypeId = userTypeId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", userCode=" + userCode +
                ", userStatus=" + userStatus +
                ", passwordEncoder='" + passwordEncoder + '\'' +
                '}';
    }
}