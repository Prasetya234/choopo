package com.example.choopo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="body")
public class Body {
    private long bodyId;

    private String bodyContent;

    private String articleId;

    private BodyType bodyType;

    public Body(){

    }

    public Body(String bodyContent, String articleId) {
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

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.MERGE)
    @JoinColumn(name = "body_type_id", nullable = false)
    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    @Override
    public String toString() {
        return "Body{" +
                "bodyId=" + bodyId +
                ", bodyContent='" + bodyContent + '\'' +
                ", articleId='" + articleId + '\'' +
                '}';
    }
}