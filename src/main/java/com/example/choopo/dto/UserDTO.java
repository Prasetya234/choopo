package com.example.choopo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {

    private Long userId;

    @NotNull
    private int userType;

    private String username;

    @NotNull
    private int userCode;

    private String password;

    @NotNull
    private int userStatus;

    private String passwordNonEncode;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public String getPasswordNonEncode() {
        return passwordNonEncode;
    }

    public void setPasswordNonEncode(String passwordNonEncode) {
        this.passwordNonEncode = passwordNonEncode;
    }
}

