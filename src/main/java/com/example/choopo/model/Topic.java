package com.example.choopo.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="topic")
public class Topic {
    private long topicId;


    private String topicName;

    @NotNull
    private int topicCode;

    public Topic(){}

    public Topic(String topicName, int topicCode) {
        this.topicName = topicName;
        this.topicCode = topicCode;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "topic_id")
    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    @Column(name = "topic_name", nullable = false)
    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    @Column(name = "topic_code", nullable = false)
    public int getTopicCode() {
        return topicCode;
    }

    public void setTopicCode(int topicCode) {
        this.topicCode = topicCode;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topicId=" + topicId +
                ", topicName='" + topicName + '\'' +
                ", topicCode=" + topicCode +
                '}';
    }
}
