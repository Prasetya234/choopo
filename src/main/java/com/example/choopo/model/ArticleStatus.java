package com.example.choopo.model;

import javax.persistence.*;
import javax.validation.constraints.*;

public class ArticleStatus {
    private Long articleStatusId;

    @NotNull
    @Size(max = 255, message = "articleStatusName has exceeded the limit")
    private String articleStatusName;

    @NotNull
    private int articleStatusCode;

    public ArticleStatus(){}

    public ArticleStatus(String articleStatusName, int articleStatusCode) {
        this.articleStatusName = articleStatusName;
        this.articleStatusCode = articleStatusCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getArticleStatusId() {
        return articleStatusId;
    }

    public void setArticleStatusId(Long articleStatusId) {
        this.articleStatusId = articleStatusId;
    }

    @Column
    public String getArticleStatusName() {
        return articleStatusName;
    }

    public void setArticleStatusName(String articleStatusName) {
        this.articleStatusName = articleStatusName;
    }

    @Column
    public int getArticleStatusCode() {
        return articleStatusCode;
    }

    public void setArticleStatusCode(int articleStatusCode) {
        this.articleStatusCode = articleStatusCode;
    }
}
