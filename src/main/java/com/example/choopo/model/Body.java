package com.example.choopo.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity(name="body")
@Table(name="body")
public class Body {
    private long bodyId;

    @NotNull
    private int bodyType;
    @NotBlank
    @Size(min = 1,max = 255, message = "body has exceeded the limit")
    private String bodyContent;

    @NotBlank
    @Size(min = 1, max = 255, message = "body has exceeded the limit")
    private String articleId;
    public Body(){

    }

    public Body(int bodyType, String bodyContent, String articleId) {
        this.bodyType = bodyType;
        this.bodyContent = bodyContent;
        this.articleId = articleId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "body_id")
    public long getBodyId() {
        return bodyId;
    }

    public void setBodyId(long bodyId) {
        this.bodyId = bodyId;
    }

    @Column(name = "body_type", nullable = false)
    public int getBodyType() {
        return bodyType;
    }

    public void setBodyType(int bodyType) {
        this.bodyType = bodyType;
    }

    @Column(name = "body_content", nullable = false)
    public String getBodyContent() {
        return bodyContent;
    }

    public void setBodyContent(String bodyContent) {
        this.bodyContent = bodyContent;
    }

    @Column(name = "article_id" , nullable = false)
    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    @Override
    public String toString() {
        return "Body{" +
                "bodyId=" + bodyId +
                ", bodyType=" + bodyType +
                ", bodyContent='" + bodyContent + '\'' +
                ", articleId='" + articleId + '\'' +
                '}';
    }
}
