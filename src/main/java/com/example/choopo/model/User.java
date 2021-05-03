package com.example.choopo.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "useres")
public class User {
    private long user_id;

    @NotNull
    private int user_type;

    @NotBlank
    @Size(min = 1, max = 255, message = "Username has exceeded the limit")
    private String user_name;

    @NotNull
    private int user_code;

    @NotBlank
    @Size(min=2, max = 20, message = "The password must be 4 - 20 words long")
    private String password;

    @NotNull
    private int user_status;

    public User(){

    }

    public User(int user_type, String user_name, int user_code, String password, int user_status) {
        this.user_type = user_type;
        this.user_name = user_name;
        this.user_code = user_code;
        this.password = password;
        this.user_status = user_status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    @Column(name = "user_type", nullable = false)
    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    @Column(name = "user_name", nullable = false)
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @Column(name = "user_code", nullable = false)
    public int getUser_code() {
        return user_code;
    }

    public void setUser_code(int user_code) {
        this.user_code = user_code;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "user_status", nullable = false)
    public int getUser_status() {
        return user_status;
    }

    public void setUser_status(int user_status) {
        this.user_status = user_status;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_type=" + user_type +
                ", user_name='" + user_name + '\'' +
                ", user_code=" + user_code +
                ", password='" + password + '\'' +
                ", user_status=" + user_status +
                '}';
    }
}
