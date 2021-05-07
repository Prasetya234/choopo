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
    private long articleId;

    @NotNull
    private int articleStatus;

    @NotNull
    private int categoryId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @NotBlank
    @Size(min = 1,max = 255, message = "subtitle has exceeded the limit")
    private String subtitle;

    @NotBlank
    @Size(min = 1,max = 255, message = "title has exceeded the limit")
    private String title;

    @NotBlank
    @Size(min = 1, max = 255, message = "main title has exceeded the limit")
    private String mainImage;

    @NotBlank
    @Size(min = 1,max = 255, message = "topic has exceeded the limit")
    private String topic;

    @NotNull
    private int totalView;

    // Constructor
    public Article() {

    }

    // Constructor with Param


    public Article(int articleStatus, int categoryId, String subtitle, String title, String mainImage, String topic ) {
        this.articleStatus = articleStatus;
        this.categoryId = categoryId;
        this.subtitle = subtitle;
        this.title = title;
        this.mainImage = mainImage;
        this.topic = topic;
    }

    // Getter and Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "article_id")
    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    @Column(name = "article_status", nullable = false)
    public int getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(int articleStatus) {
        this.articleStatus = articleStatus;
    }

    @Column(name = "category_id", nullable = false)
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Column(name = "created_date")
    @CreationTimestamp
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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
    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    @Column(name = "topic", nullable = false)
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Column(name = "total_view", nullable = false)
    public int getTotalView() {
        return totalView;
    }

    public void setTotalView(int totalView) {
        this.totalView = totalView;
    }


    @Override
    public int hashCode(){
        int total = 1 + Objects.hashCode(this.totalView);
        return total;
    }

    /**
     * Returns JSON string with id and your data
     * Implementation can change in future, not to rely to convert object to JSON
     */

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", articleStatus=" + articleStatus +
                ", categoryId=" + categoryId +
                ", createdDate=" + createdDate +
                ", subtitle='" + subtitle + '\'' +
                ", title='" + title + '\'' +
                ", mainImage='" + mainImage + '\'' +
                ", topic='" + topic + '\'' +
                ", totalView=" + totalView +
                '}';
    }
}