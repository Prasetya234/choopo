package com.example.choopo.dto;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserStatusDTO {

    private long userStatusId;

    private String userStatusName;

    private int userStatusCode;

    // GET & SET
    public long getUserStatusId() {
        return userStatusId;
    }

    public void setUserStatusId(long userStatusId) {
        this.userStatusId = userStatusId;
    }

    public String getUserStatusName() {
        return userStatusName;
    }

    public void setUserStatusName(String userStatusName) {
        this.userStatusName = userStatusName;
    }

    public int getUserStatusCode() {
        return userStatusCode;
    }

    public void setUserStatusCode(int userStatusCode) {
        this.userStatusCode = userStatusCode;
    }
}
