package com.example.choopo.service;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Topic;

import java.util.List;
import java.util.Map;

public interface TopicService {

    List<Topic> getAll();

    Topic createTopic(Topic topicRequire);

    Topic getTopicById(Long topicId) throws ResourceNotFoundExceotion;

    Topic updateTopicById(Long topicId, Topic topicDetails) throws ResourceNotFoundExceotion;

    Map<String, Boolean> deleteTopicById(Long topicId) throws  ResourceNotFoundExceotion;
}
