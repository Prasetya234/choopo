package com.example.choopo.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="user_status")
public class UserStatus {

    private long userStatusId;

    private String userStatusName;

    private int userStatusCode;

    public UserStatus(){

    }

    public UserStatus(String userStatusName, int userStatusCode) {
        this.userStatusName = userStatusName;
        this.userStatusCode = userStatusCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_status_id")
    public long getUserStatusId() {
        return userStatusId;
    }

    public void setUserStatusId(long userStatusId) {
        this.userStatusId = userStatusId;
    }

    @Column(name = "user_status_name", nullable = false)
    public String getUserStatusName() {
        return userStatusName;
    }

    public void setUserStatusName(String userStatusName) {
        this.userStatusName = userStatusName;
    }

    @Column(name = "user_status_code", nullable = false)
    public int getUserStatusCode() {
        return userStatusCode;
    }

    public void setUserStatusCode(int userStatusCode) {
        this.userStatusCode = userStatusCode;
    }

    @Override
    public String toString() {
        return "UserStatus{" +
                "userStatusId=" + userStatusId +
                ", userStatusName='" + userStatusName + '\'' +
                ", userStatusCode=" + userStatusCode +
                '}';
    }
}
