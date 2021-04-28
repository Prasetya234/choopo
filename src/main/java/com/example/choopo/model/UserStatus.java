package com.example.choopo.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="user_status")
public class UserStatus {

    private Long user_status_id;

    @NotNull
    @Size(max= 255, message = "user_status_name has exceeded the limit")
    private String user_status_name;

    @NotNull
    private int user_status_code;

    public  UserStatus(){

    }

    public UserStatus(String user_status_name, int user_status_code) {
        this.user_status_name = user_status_name;
        this.user_status_code = user_status_code;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getUser_status_id() {
        return user_status_id;
    }

    public void setUser_status_id(Long user_status_id) {
        this.user_status_id = user_status_id;
    }

    @Column(name = "user_status_name", nullable = false)
    public String getUser_status_name() {
        return user_status_name;
    }

    public void setUser_status_name(String user_status_name) {
        this.user_status_name = user_status_name;
    }

    @Column(name = "user_status_code", nullable = false)
    public int getUser_status_code() {
        return user_status_code;
    }

    public void setUser_status_code(int user_status_code) {
        this.user_status_code = user_status_code;
    }

    @Override
    public String toString() {
        return "UserStatus{" +
                "user_status_id=" + user_status_id +
                ", user_status_name='" + user_status_name + '\'' +
                ", user_status_code=" + user_status_code +
                '}';
    }
}
