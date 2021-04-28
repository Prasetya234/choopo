package com.example.choopo.model;

import javax.persistence.Column;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="body")
public class Body {
    private Long body_id;

    @NotNull
    private int body_type;
    @NotNull
    @Size(max = 255, message = "article has exceeded the limit")
    private String body_content;

    public Body(){}

    public Body(int body_type, String body_content) {
        this.body_type = body_type;
        this.body_content = body_content;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getBody_id() {
        return body_id;
    }

    public void setBody_id(Long body_id) {
        this.body_id = body_id;
    }

    @Column(name = "body_type", nullable = false)
    public int getBody_type() {
        return body_type;
    }

    public void setBody_type(int body_type) {
        this.body_type = body_type;
    }

    @Column(name = "body_content", nullable = false)
    public String getBody_content() {
        return body_content;
    }

    public void setBody_content(String body_content) {
        this.body_content = body_content;
    }

    @Override
    public String toString() {
        return "Body{" +
                "body_id=" + body_id +
                ", body_type=" + body_type +
                ", body_content='" + body_content + '\'' +
                '}';
    }
}
