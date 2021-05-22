package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Topic;
import com.example.choopo.repository.TopicRepository;
import com.example.choopo.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired private TopicRepository topicRepository;

    @Autowired private TopicService topicService;

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getAll() {
        return topicService.getAll();
    }

    @PostMapping("/")
    public Topic createTopic (@Valid @RequestBody Topic topic){
        return topicService.createTopic(topic);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable (value = "id")Long topic_id) throws ResourceNotFoundExceotion {
        return topicService.getTopicById(topic_id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topic> updateTopicById(@PathVariable (value = "id")Long topic_id, @Valid @RequestBody Topic topicDetails) throws ResourceNotFoundExceotion {
        return topicService.updateTopicById(topic_id,topicDetails);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteTopicById(@PathVariable (value = "id") Long topic_id) throws ResourceNotFoundExceotion {
        return topicService.deleteTopicById(topic_id);
    }
}