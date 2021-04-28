package com.example.choopo.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "user_type")
public class UserType {
    private long user_type_id;

    @NotNull
    @Size(max = 255, message = "user_type_name has exceeded the limit")
    private String user_type_name;

    @NotNull
    private int user_type_code;

    public UserType(){

    }

    public UserType(String user_type_name, int user_type_code) {
        this.user_type_name = user_type_name;
        this.user_type_code = user_type_code;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getUser_type_id() {
        return user_type_id;
    }

    public void setUser_type_id(long user_type_id) {
        this.user_type_id = user_type_id;
    }

    @Column(name = "user_type_name", nullable = false)
    public String getUser_type_name() {
        return user_type_name;
    }

    public void setUser_type_name(String user_type_name) {
        this.user_type_name = user_type_name;
    }

    @Column(name = "user_type_code", nullable = false)
    public int getUser_type_code() {
        return user_type_code;
    }

    public void setUser_type_code(int user_type_code) {
        this.user_type_code = user_type_code;
    }

    @Override
    public String toString() {
        return "UserType{" +
                "user_type_id=" + user_type_id +
                ", user_type_name='" + user_type_name + '\'' +
                ", user_type_code=" + user_type_code +
                '}';
    }
}
