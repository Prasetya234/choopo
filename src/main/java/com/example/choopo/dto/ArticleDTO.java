package com.example.choopo.dto;

import com.example.choopo.model.ArticleStatus;
import com.example.choopo.model.Category;
import com.fasterxml.jackson.annotation.JsonFormat;

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

    @NotNull
    private int totalView;

    @NotNull(message = "DATA TIDAK BOLEH KOSONG KOSONG")
    private ArticleStatus articleStatus;

    @NotNull(message = "DATA TIDAK BOLEH KOSONG KOSONG")
    private Category category;


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

    public ArticleStatus getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(ArticleStatus articleStatus) {
        this.articleStatus = articleStatus;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}