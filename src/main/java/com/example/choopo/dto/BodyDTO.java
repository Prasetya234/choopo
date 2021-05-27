package com.example.choopo.dto;

import com.example.choopo.model.BodyType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BodyDTO {

    private long bodyId;

    @NotBlank
    @Size(min = 1,max = 255, message = "body has exceeded the limit")
    private String bodyContent;

    @NotBlank
    @Size(min = 1, max = 255, message = "body has exceeded the limit")
    private String articleId;

    @NotNull(message = "DATA TIDAK BOLEH KOSONG")
    private String bodyType;

//    @NotNull(message = "DATA TIDAK BOLEH KOSONG")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private BodyType bodyTypeId;

    // GET & SET

    public long getBodyId() {
        return bodyId;
    }

    public void setBodyId(long bodyId) {
        this.bodyId = bodyId;
    }

    public String getBodyContent() {
        return bodyContent;
    }

    public void setBodyContent(String bodyContent) {
        this.bodyContent = bodyContent;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public BodyType getBodyTypeId() {
        return bodyTypeId;
    }

    public void setBodyTypeId(BodyType bodyTypeId) {
        this.bodyTypeId = bodyTypeId;
    }
}
