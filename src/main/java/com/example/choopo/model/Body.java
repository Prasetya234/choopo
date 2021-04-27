package com.example.choopo.model;

import javax.persistence.Column;
import javax.validation.constraints.*;

public class Body {
    private Long bodyId;

    @NotNull
    private int type;
    @NotNull
    @Size(max = 255, message = "article has exceeded the limit")
    private String content;

    public Body(){}

    public Body(int type, String content) {
        this.type = type;
        this.content = content;
    }

    @Column
    public Long getBodyId() {
        return bodyId;
    }

    public void setBodyId(Long bodyId) {
        this.bodyId = bodyId;
    }

    @Column
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Column
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
