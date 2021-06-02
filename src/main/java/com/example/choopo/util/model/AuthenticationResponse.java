package com.example.choopo.util.model;

import com.example.choopo.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "temporary_token")
public class AuthenticationResponse{

    private Long idJwt;

    private String jwt;

    private Date expiredDate;

    private String user;

    private User userUser;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_jwt")
    public Long getIdJwt() {
        return idJwt;
    }

    public void setIdJwt(Long idJwt) {
        this.idJwt = idJwt;
    }

    @Column(name = "jwt", columnDefinition = "TEXT(2000)")
    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    @Column(name = "expired_date")
    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Transient
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = true)
    public User getUserUser() {
        return userUser;
    }

    public void setUserUser(User userUser) {
        this.userUser = userUser;
    }
}
