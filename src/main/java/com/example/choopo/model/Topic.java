package com.example.choopo.model;

import javax.persistence.*;
import javax.validation.constraints.*;

public class Topic {
    private Long topicId;

    @NotNull
    @Size(max=255, message = "name has exceeded the limit")
    private String name;

    @NotNull
    private int code;

    public Topic(){}

    public Topic(String name, int code) {
        this.name = name;
        this.code = code;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
