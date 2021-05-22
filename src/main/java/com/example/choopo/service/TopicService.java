package com.example.choopo.service;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Topic;
import com.example.choopo.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class TopicService {

    @Autowired private TopicRepository topicRepository;

    // GET ALL TOPIC
    public ResponseEntity<Map<String, Object>> getAll() {

            List<Topic> topics = new ArrayList<>();

            topics = topicRepository.findAll();

            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", topics);

            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // POST TOPIC
    public Topic createTopic (Topic topic){
        return topicRepository.save(topic);
    }

    // GET TOPIC BY ID
    public ResponseEntity<Topic> getTopicById(Long topic_id) throws ResourceNotFoundExceotion {

        Optional<Topic> topic = Optional.ofNullable(topicRepository.findById(topic_id)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("TOPIC ID NOT FOUND")));

            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", topic);

            return new ResponseEntity(response, HttpStatus.OK);
    }

    // UPDATE TOPIC BY ID
    public ResponseEntity<Topic> updateTopicById(Long topic_id,  Topic topicDetails) throws ResourceNotFoundExceotion {
        Topic topic = topicRepository.findById(topic_id)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("TOPIC ID NOT FOUND"));

        topic.setTopicName(topicDetails.getTopicName());
        topic.setTopicCode(topicDetails.getTopicCode());
        final Topic updateTopicById = topicRepository.save(topic);

        return ResponseEntity.ok(updateTopicById);
    }

    // DELETE TOPIC BY ID
    public Map<String, Boolean> deleteTopicById(Long topic_id) throws ResourceNotFoundExceotion {
        Topic topic = topicRepository.findById(topic_id)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("TOPIC ID NOTFOUND"));

        topicRepository.delete(topic);

        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);

        return response;
    }
}
