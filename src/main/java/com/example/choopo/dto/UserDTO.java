package com.example.choopo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {

    private Long userId;

    @NotNull
    private int userType;

    @NotBlank
    @Size(min = 1, max = 255, message = "Username has exceeded the limit")
    private String username;

    @NotNull
    private int userCode;

    @NotBlank
    @Size(min=2, max = 20, message = "The password must be 4 - 20 words long")
    private String password;

    @NotNull
    private int userStatus;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private  String passwordEncoder;

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

    public String getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(String passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
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
}

