package com.example.choopo.util.model;

import javax.persistence.*;

@Entity
@Table(name = "temporary_token")
public class AuthenticationResponse {

    private Long idJwt;

    private String jwt;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "auto_jwt")
    @SequenceGenerator(name = "auto_jwt", sequenceName = "id_jwt")
    @Column(name = "id_jwt")
    public Long getIdJwt() {
        return idJwt;
    }

    public void setIdJwt(Long idJwt) {
        this.idJwt = idJwt;
    }


    @Column(name = "jwt")
    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
