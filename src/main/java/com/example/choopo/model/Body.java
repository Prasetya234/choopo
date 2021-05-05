package com.example.choopo.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity(name="body")
@Table(name="body")
public class Body {
    private long body_id;

    @NotNull
    private int body_type;
    @NotBlank
    @Size(min = 1,max = 255, message = "body has exceeded the limit")
    private String body_content;

    @NotBlank
    @Size(min = 1, max = 255, message = "body has exceeded the limit")
    private String article_id;
    public Body(){

    }

    public Body(int body_type, String body_content, String article_id) {
        this.body_type = body_type;
        this.body_content = body_content;
        this.article_id = article_id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getBody_id() {
        return body_id;
    }

    public void setBody_id(long body_id) {
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

    @Column(name = "article_id" , nullable = false)
    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    @Override
    public String toString() {
        return "Body{" +
                "body_id=" + body_id +
                ", body_type=" + body_type +
                ", body_content='" + body_content + '\'' +
                ", article_id='" + article_id + '\'' +
                '}';
    }
}
