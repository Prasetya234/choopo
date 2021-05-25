package com.example.choopo.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "article_status")
public class ArticleStatus {

    private long articleStatusId;

    @NotBlank
    @Size(min = 1,max = 255, message = "articleStatusName has exceeded the limit")
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
    @Column(name = "article_status_id")
    public long getArticleStatusId() {
        return articleStatusId;
    }

    public void setArticleStatusId(long articleStatusId) {
        this.articleStatusId = articleStatusId;
    }

    @Column(name = "article_status_name", nullable = false)
    public String getArticleStatusName() {
        return articleStatusName;
    }

    public void setArticleStatusName(String articleStatusName) {
        this.articleStatusName = articleStatusName;
    }

    @Column(name = "article_status_code",nullable = false)
    public int getArticleStatusCode() {
        return articleStatusCode;
    }

    public void setArticleStatusCode(int articleStatusCode) {
        this.articleStatusCode = articleStatusCode;
    }


    @Override
    public String toString() {
        return "ArticleStatus{" +
                "articleStatusId=" + articleStatusId +
                ", articleStatusName='" + articleStatusName + '\'' +
                ", articleStatusCode=" + articleStatusCode +
                '}';
    }
}
