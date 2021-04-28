package com.example.choopo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;


@Entity
@Table(name="article")
public class Article {

    private Long article_id;

    @NotNull
    @Size(max = 255, message = "category has exceeded the limit")
    private int article_status;

    @NotNull
    @Size(max = 255, message = "article has exceeded the limit")
    private int category_id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Jakarta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_date;

    @NotNull
    @Size(max = 255, message = "subtitle has exceeded the limit")
    private String subtitle;

    @NotNull
    @Size(max = 255, message = "title has exceeded the limit")
    private String title;

    @NotNull
    @Size(max = 255, message = "topic has exceeded the limit")
    private String topic;

    @NotNull
    @Size(max = 255, message = "title_view has exceeded the limit")
    private int total_view;


    public Article() {

    }

    public Article(int article_status, int category_id, Date created_date, String subtitle, String title, String topic, int total_view) {
        this.article_status = article_status;
        this.category_id = category_id;
        this.created_date = created_date;
        this.subtitle = subtitle;
        this.title = title;
        this.topic = topic;
        this.total_view = total_view;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Long article_id) {
        this.article_id = article_id;
    }

    @Column
    public int getArticle_status() {
        return article_status;
    }

    public void setArticle_status(int article_status) {
        this.article_status = article_status;
    }

    @Column
    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    @Column
    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    @Column
    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @Column
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Column
    public int getTotal_view() {
        return total_view;
    }

    public void setTotal_view(int total_view) {
        this.total_view = total_view;
    }
}
