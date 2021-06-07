package com.example.choopo.util.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "temporary_token")
public class TemporaryToken {

    private Long idToken;

    private String token;

    private Date expiredDate;

    private String user;

//    private User userUser;

    public TemporaryToken(){
        
    }
    public TemporaryToken(String token, Date expiredDate, String user) {
        this.token = token;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_token")
    public Long getIdToken() {
        return idToken;
    }

    public void setIdToken(Long idToken) {
        this.idToken = idToken;
    }

    @Column(name = "token", nullable = false)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Column(name = "expired_date", nullable = false)
    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "user", nullable = false)
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "TemporaryToken{" +
                "idToken=" + idToken +
                ", token='" + token + '\'' +
                ", expiredDate=" + expiredDate +
                ", user='" + user + '\'' +
                '}';
    }
}
