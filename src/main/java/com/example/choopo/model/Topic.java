package com.example.choopo.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="topic")
public class Topic {
    private long topic_id;

    @NotNull
    @Size(max=255, message = "name has exceeded the limit")
    private String topic_name;

    @NotNull
    private int topic_code;

    public Topic(){}

    public Topic(String topic_name, int topic_code) {
        this.topic_name = topic_name;
        this.topic_code = topic_code;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(long topic_id) {
        this.topic_id = topic_id;
    }

    @Column(name = "topic_name", nullable = false)
    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    @Column(name = "topic_code", nullable = false)
    public int getTopic_code() {
        return topic_code;
    }

    public void setTopic_code(int topic_code) {
        this.topic_code = topic_code;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topic_id=" + topic_id +
                ", topic_name='" + topic_name + '\'' +
                ", topic_code=" + topic_code +
                '}';
    }
}
