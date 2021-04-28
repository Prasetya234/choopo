package com.example.choopo.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "body_type")
public class BodyType {

    private long body_type_id;

    @NotNull
    @Size(min = 1,max = 255,message = "BodyTypeName has exceeded the limit")
    private String body_type_name;

    @NotNull
    private int body_type_code;

    public BodyType(){

    }

    public BodyType(String body_type_name, int body_type_code) {
        this.body_type_name = body_type_name;
        this.body_type_code = body_type_code;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getBody_type_id() {
        return body_type_id;
    }

    public void setBody_type_id(long body_type_id) {
        this.body_type_id = body_type_id;
    }

    @Column(name = "body_type_name", nullable = false)
    public String getBody_type_name() {
        return body_type_name;
    }

    public void setBody_type_name(String body_type_name) {
        this.body_type_name = body_type_name;
    }

    @Column(name = "body_type_code", nullable = false)
    public int getBody_type_code() {
        return body_type_code;
    }

    public void setBody_type_code(int body_type_code) {
        this.body_type_code = body_type_code;
    }

    @Override
    public String toString() {
        return "BodyType{" +
                "body_type_id=" + body_type_id +
                ", body_type_name='" + body_type_name + '\'' +
                ", body_type_code=" + body_type_code +
                '}';
    }
}
