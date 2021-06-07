package com.example.choopo.engineer.dto;

import java.util.Date;

public class TemporaryTokenDTO {

    private Long idToken;

    private String token;

    private Date expiredDate;

    private String user;

    // GET & SET
    public Long getIdToken() {
        return idToken;
    }

    public void setIdToken(Long idToken) {
        this.idToken = idToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
