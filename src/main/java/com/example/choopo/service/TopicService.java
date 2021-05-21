package com.example.choopo.service;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Topic;
import com.example.choopo.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.*;
@Service
public class TopicService {

    @Autowired private TopicRepository topicRepository;

    // GET ALL TOPIC
    public ResponseEntity<Map<String, Object>> getAll() {
        try {
            List<Topic> topics = new ArrayList<>();

            topics = topicRepository.findAll();

            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", topics);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // POST TOPIC
    public Topic createTopic (@Valid @RequestBody Topic topic){
        return topicRepository.save(topic);
    }

    // GET TOPIC BY ID
    public ResponseEntity<Topic> getTopicById(@PathVariable(value = "id")Long topic_id) throws ResourceNotFoundExceotion {

        Optional<Topic> topic = Optional.ofNullable(topicRepository.findById(topic_id).orElseThrow(() -> new ResourceNotFoundExceotion("TOPIC ID NOT FOUND")));

        try {
            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", topic);

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            ResourceNotFoundExceotion message = new ResourceNotFoundExceotion("TOPIC ID NOT FOUND");
            return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
        }
    }

    // UPDATE TOPIC BY ID
    public ResponseEntity<Topic> updateTopicById(@PathVariable (value = "id")Long topic_id,  Topic topicDetails)
            throws ResourceNotFoundExceotion {
        Topic topic = topicRepository.findById(topic_id).orElseThrow(() -> new ResourceNotFoundExceotion("TOPIC ID NOT FOUND"));

        topic.setTopicName(topicDetails.getTopicName());
        topic.setTopicCode(topicDetails.getTopicCode());
        final Topic updateTopicById = topicRepository.save(topic);
        return ResponseEntity.ok(updateTopicById);
    }

    // DELETE TOPIC BY ID
    public Map<String, Boolean> deleteTopicById(@PathVariable (value = "id") Long topic_id)
            throws ResourceNotFoundExceotion {
        Topic topic = topicRepository.findById(topic_id).orElseThrow(() -> new ResourceNotFoundExceotion("TOPIC ID NOTFOUND"));

        topicRepository.delete(topic);
        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);
        return response;
    }
}
