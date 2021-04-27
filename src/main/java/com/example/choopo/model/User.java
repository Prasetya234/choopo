package com.example.choopo.model;

import javax.persistence.*;
import javax.validation.constraints.*;

public class User {
    private Long userId;

    @NotNull
    private int type;

    @NotNull
    @Size(max = 255, message = "Username has exceeded the limit")
    private String userName;

    @NotNull
    private int userCode;

    @NotNull
    @Size(min=4, max = 20, message = "The password must be 4 - 20 words long")
    private String password;

    public User(){}

    public User(int type, String userName, int userCode, String password) {
        this.type = type;
        this.userName = userName;
        this.userCode = userCode;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Column
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column
    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    @Column
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
