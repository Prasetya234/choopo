package com.example.choopo.dto;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class ArticleStatusDTO {

    @Id
    private Long articleStatusId;

    @NotNull
    private String articleStatusName;

    @NotNull
    private int articleStatusCode;

    // GETTER & SETTER
    public Long getArticleStatusId() {
        return articleStatusId;
    }

    public void setArticleStatusId(Long articleStatusId) {
        this.articleStatusId = articleStatusId;
    }

    public String getArticleStatusName() {
        return articleStatusName;
    }

    public void setArticleStatusName(String articleStatusName) {
        this.articleStatusName = articleStatusName;
    }

    public int getArticleStatusCode() {
        return articleStatusCode;
    }

    public void setArticleStatusCode(int articleStatusCode) {
        this.articleStatusCode = articleStatusCode;
    }
}
