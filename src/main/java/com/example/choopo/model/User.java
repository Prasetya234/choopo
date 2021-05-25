package com.example.choopo.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "useres")
public class User {
    private long userId;

    @NotNull
    private int userType;

    @NotBlank
    @Size(min = 1, max = 255, message = "Username has exceeded the limit")
    private String userName;

    @NotNull
    private int userCode;

    @NotBlank
    @Size(min=2, max = 20, message = "The password must be 4 - 20 words long")
    private String password;

    @NotNull
    private int userStatus;

    public User(){

    }

    public User(int userType, String userName, int userCode, String password, int userStatus) {
        this.userType = userType;
        this.userName = userName;
        this.userCode = userCode;
        this.password = password;
        this.userStatus = userStatus;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Column(name = "user_name", nullable = false)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "user_code", nullable = false)
    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    @Column(name = "password", nullable = false)
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

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userType=" + userType +
                ", userName='" + userName + '\'' +
                ", userCode=" + userCode +
                ", password='" + password + '\'' +
                ", userStatus=" + userStatus +
                '}';
    }
}