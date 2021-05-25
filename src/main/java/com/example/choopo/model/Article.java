package com.example.choopo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

// Model Article

@Entity
@Table(name = "article")
public class Article {

    private long articleId;

    private Date createdDate;

    private String subtitle;

    private String title;

    private String mainImage;

    private String topic;

    private int totalView;

    private ArticleStatus articleStatus;

    private Category category;

    // Constructor
    public Article() {

    }

    public Article(String subtitle, String title, String mainImage, String topic) {
        this.subtitle = subtitle;
        this.title = title;
        this.mainImage = mainImage;
        this.topic = topic;
    }

    // Getter and Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "auto_gen")
    @SequenceGenerator(name = "auto_gen", sequenceName = "article_id")
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

    @Lob
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

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.MERGE)
    @JoinColumn(name = "article_status_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public ArticleStatus getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(ArticleStatus articleStatus) {
        this.articleStatus = articleStatus;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.MERGE)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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