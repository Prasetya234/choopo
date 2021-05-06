package com.example.choopo.model;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.sql.*;
import java.util.Objects;

// Model

@Entity
@Table(name="article")
public class Article {

    // variable declaration or called Fields
    private long article_id;

    @NotNull
    private int article_status;

    @NotNull
    private int category_id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_date;

    @NotBlank
    @Size(min = 1,max = 255, message = "subtitle has exceeded the limit")
    private String subtitle;

    @NotBlank
    @Size(min = 1,max = 255, message = "title has exceeded the limit")
    private String title;

    @NotBlank
    @Size(min = 1, max = 255, message = "main title has exceeded the limit")
    private String main_image;

    @NotBlank
    @Size(min = 1,max = 255, message = "topic has exceeded the limit")
    private String topic;

    @NotNull
    private int total_view;

    // Constructor
    public Article() {

    }

    // Constructor with Param
    public Article(int article_status, int category_id, String subtitle, String title, String main_image, String topic) {
        this.article_status = article_status;
        this.category_id = category_id;
        this.subtitle = subtitle;
        this.title = title;
        this.main_image = main_image;
        this.topic = topic;
    }

    // Getter and Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(long article_id) {
        this.article_id = article_id;
    }

    @Column(name = "article_status", nullable = false)
    public int getArticle_status() {
        return article_status;
    }

    public void setArticle_status(int article_status) {
        this.article_status = article_status;
    }

    @Column(name = "category_id", nullable = false)
    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    @Column
    @CreationTimestamp
    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    @Column(name = "subtitle", nullable = false)
    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "main_image", nullable = false)
    public String getMain_image() {
        return main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }

    @Column(name = "topic", nullable = false)
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Column(name = "total_view", nullable = false)
    public int getTotal_view() {
        return total_view;
    }

    public void setTotal_view(int total_view) {
        this.total_view = total_view;
    }


    @Override
    public int hashCode(){
        int total = 1 + Objects.hashCode(this.total_view);
        return total;
    }

    /**
     * Returns JSON string with id and your data
     * Implementation can change in future, not to rely to convert object to JSON
     */
    @Override
    public String toString() {
        return "Article{" +
                "article_id=" + article_id +
                ", article_status=" + article_status +
                ", category_id=" + category_id +
                ", created_date=" + created_date +
                ", subtitle='" + subtitle + '\'' +
                ", title='" + title + '\'' +
                ", main_image='" + main_image + '\'' +
                ", topic='" + topic + '\'' +
                ", total_view=" + total_view +
                '}';
    }
}
