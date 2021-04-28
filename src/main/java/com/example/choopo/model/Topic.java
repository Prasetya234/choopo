package com.example.choopo.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="topic")
public class Topic {
    private Long topic_id;

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
    public Long getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(Long topic_id) {
        this.topic_id = topic_id;
    }

    @Column
    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    @Column
    public int getTopic_code() {
        return topic_code;
    }

    public void setTopic_code(int topic_code) {
        this.topic_code = topic_code;
    }
}
