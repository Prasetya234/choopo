package com.example.choopo.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.sql.Date;

// Model Article

@Entity
@Table(name="article")
public class Article {

    // variable declaration or called Fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long articleId;


    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @NotBlank(message = "DATA TIDAK BOLEH KOSONG KOSONG")
    @Size(max = 255, message = "subtitle has exceeded the limit")
    private String subtitle;

    @NotBlank(message = "DATA TIDAK BOLEH KOSONG KOSONG")
    @Size(max = 255, message = "title has exceeded the limit")
    private String title;

    @NotBlank(message = "DATA TIDAK BOLEH KOSONG KOSONG")
    @Size(max = 1000000, message = "main title has exceeded the limit")
    private String mainImage;

    @NotBlank(message = "DATA TIDAK BOLEH KOSONG KOSONG")
    @Size(max = 255, message = "topic has exceeded the limit")
    private String topic;

    @NotNull
    private int totalView;

    @NotNull(message = "DATA TIDAK BOLEH KOSONG KOSONG")
    private ArticleStatus articleStatus;

    @NotNull(message = "DATA TIDAK BOLEH KOSONG KOSONG")
    private Category category;

    // Constructor
    public Article() {

    }

    public Article(Date createdDate, String subtitle, String title, String mainImage, String topic) {
        this.createdDate = createdDate;
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

    @Column(name = "main_image",columnDefinition = "TEXT(1000000)", nullable = false)
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

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.MERGE)
    @JoinColumn(name = "article_status_id", nullable = false)
    public ArticleStatus getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(ArticleStatus articleStatus) {
        this.articleStatus = articleStatus;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.MERGE)
    @JoinColumn(name = "category", nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Returns JSON string with id and your data
     * Implementation can change in future, not to rely to convert object to JSON
     */
    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", createdDate=" + createdDate +
                ", subtitle='" + subtitle + '\'' +
                ", title='" + title + '\'' +
                ", mainImage='" + mainImage + '\'' +
                ", topic='" + topic + '\'' +
                ", totalView=" + totalView +
                '}';
    }
}