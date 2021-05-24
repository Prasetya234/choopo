package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Topic;
import com.example.choopo.service.TopicImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired private TopicImpl topicService;

    @GetMapping("/")
    public List<Topic> getAll() {
        return topicService.getAll();
    }

    @PostMapping("/")
    public Topic createTopic (@Valid @RequestBody Topic topicRequire){
        return topicService.createTopic(topicRequire);
    }

    @GetMapping("/{id}")
    public Topic getTopicById(@PathVariable (value = "id")Long topicId) throws ResourceNotFoundExceotion {
        return topicService.getTopicById(topicId);
    }

    @PutMapping("/{id}")
    public Topic updateTopicById(@PathVariable(value = "id") Long topicId, @Valid @RequestBody Topic topicDetails) throws ResourceNotFoundExceotion {
        return topicService.updateTopicById(topicId, topicDetails);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteTopicById(@PathVariable (value = "id") Long topicId) throws ResourceNotFoundExceotion {
        return topicService.deleteTopicById(topicId);
    }
}