package com.example.choopo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "useres")
public class User {
    private long userId;

    private int userType;

    private String username;

    private int userCode;

    private String password;

    private String passwordNonEncode;

    private int userStatus;

    public User(){

    }

    public User(int userType, String username, int userCode, String password, String passwordNonEncode, int userStatus) {
        this.userType = userType;
        this.username = username;
        this.userCode = userCode;
        this.password = password;
        this.passwordNonEncode = passwordNonEncode;
        this.userStatus = userStatus;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Column(name = "user_type", nullable = false)
    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @NotEmpty(message = "USERNAME HARUS DI ISI")
    @Size(min = 4, max = 10, message = "USERNAME MUST 4 - 10 CHARACTERS")
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty(message = "PASSWORD HARUS DI ISI")
    @Size(min=4, max = 20, message = "PASSWORD MUST 4 - 20 CHARACTERS")
    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "password_non_encode", nullable = true)
    public String getPasswordNonEncode() {
        return passwordNonEncode;
    }

    public void setPasswordNonEncode(String passwordNonEncode) {
        this.passwordNonEncode = passwordNonEncode;
    }

    @Column(name = "user_status", nullable = false)
    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }



    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userType=" + userType +
                ", username='" + username + '\'' +
                ", userCode=" + userCode +
                ", passwordNonEncode='" + passwordNonEncode + '\'' +
                ", userStatus=" + userStatus +
                '}';
    }
}