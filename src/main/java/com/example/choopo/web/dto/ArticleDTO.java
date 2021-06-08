package com.example.choopo.web.dto;

import com.example.choopo.web.model.ArticleStatus;
import com.example.choopo.web.model.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class ArticleDTO {

    private long articleId;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Jakarta")
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

    @NotNull(message = "TOTAL VIEWS")
    private int totalView;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String articleStatus;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "DATA TIDAK BOLEH KOSONG")
    private String category;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ArticleStatus articleStatusId;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Category categoryId;

    @NotNull
    private boolean isDeleted;

    @NotNull
    private boolean isTakedown;

    // GET & SET

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getTotalView() {
        return totalView;
    }

    public void setTotalView(int totalView) {
        this.totalView = totalView;
    }

    public String getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(String articleStatus) {
        this.articleStatus = articleStatus;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArticleStatus getArticleStatusId() {
        return articleStatusId;
    }

    public void setArticleStatusId(ArticleStatus articleStatusId) {
        this.articleStatusId = articleStatusId;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean isTakedown() {
        return isTakedown;
    }

    public void setTakedown(boolean isTakedown) {
        this.isTakedown = isTakedown;
    }
}
