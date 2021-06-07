package com.example.choopo.web.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
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

    private String articleStatus;

    private String category;

    private ArticleStatus articleStatusId;

    private Category categoryId;

    private boolean isDeleted = false;

    private boolean isTakedown = false;

    // Constructor
    public Article() {

    }

    public Article(String subtitle, String title, String mainImage, String topic, String articleStatus, String category, boolean isDeleted, boolean isTakedown) {
        this.subtitle = subtitle;
        this.title = title;
        this.mainImage = mainImage;
        this.topic = topic;
        this.articleStatus = articleStatus;
        this.category = category;
        this.isDeleted = isDeleted;
        this.isTakedown = isTakedown;
    }

    // Getter and Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "auto_article")
    @SequenceGenerator(name = "auto_article", sequenceName = "article_id")
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
    @Type(type = "org.hibernate.type.TextType")
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

    @Column(name = "article_status_implements")
    public String getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(String articleStatus) {
        this.articleStatus = articleStatus;
    }

    @Column(name = "category_implements")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.MERGE)
    @JoinColumn(name = "article_status_id", nullable = false)
    public ArticleStatus getArticleStatusId() {
        return articleStatusId;
    }

    public void setArticleStatusId(ArticleStatus articleStatusId) {
        this.articleStatusId = articleStatusId;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.MERGE)
    @JoinColumn(name = "category_id", nullable = false)
    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    @Column(name = "is_deleted")
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Column(name = "is_takedown")
    public boolean isTakedown() {
        return isTakedown;
    }

    public void setTakedown(boolean takedown) {
        isTakedown = takedown;
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