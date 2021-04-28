package com.example.choopo.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "article_status")
public class ArticleStatus {
    private Long article_status_id;

    @NotNull
    @Size(max = 255, message = "articleStatusName has exceeded the limit")
    private String article_status_name;

    @NotNull
    private int article_status_code;

    public ArticleStatus(){}

    public ArticleStatus(String article_status_name, int article_status_code) {
        this.article_status_name = article_status_name;
        this.article_status_code = article_status_code;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getArticle_status_id() {
        return article_status_id;
    }

    public void setArticle_status_id(Long article_status_id) {
        this.article_status_id = article_status_id;
    }

    @Column
    public String getArticle_status_name() {
        return article_status_name;
    }

    public void setArticle_status_name(String article_status_name) {
        this.article_status_name = article_status_name;
    }

    @Column
    public int getArticle_status_code() {
        return article_status_code;
    }

    public void setArticle_status_code(int article_status_code) {
        this.article_status_code = article_status_code;
    }
}
