package com.example.choopo.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "user_type")
public class UserType {
    private long userTypeId;

    private String userTypeName;

    private int userTypeCode;

    public UserType(){

    }

    public UserType(String userTypeName, int userTypeCode) {
        this.userTypeName = userTypeName;
        this.userTypeCode = userTypeCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_type_id")
    public long getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(long userTypeId) {
        this.userTypeId = userTypeId;
    }

    @Column(name = "user_type_name", nullable = false)
    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    @Column(name = "user_type_code", nullable = false)
    public int getUserTypeCode() {
        return userTypeCode;
    }

    public void setUserTypeCode(int userTypeCode) {
        this.userTypeCode = userTypeCode;
    }

    @Override
    public String toString() {
        return "UserType{" +
                "userTypeId=" + userTypeId +
                ", userTypeName='" + userTypeName + '\'' +
                ", userTypeCode=" + userTypeCode +
                '}';
    }
}
