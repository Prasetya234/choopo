package com.example.choopo.controller;

import com.example.choopo.dto.ArticleStatusDTO;
import com.example.choopo.dto.TopicDTO;
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
    public TopicDTO createTopic (@Valid @RequestBody TopicDTO topicDTO){
        return topicService.createTopic(topicDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDTO> getTopicById(@PathVariable (value = "id")Long topic_id) throws ResourceNotFoundExceotion {
        return topicService.getTopicById(topic_id);
    }

    @PutMapping("/{id}")
    public TopicDTO updateTopicById(@PathVariable(value = "id") Long topic_id, @Valid @RequestBody TopicDTO topicDTODetails) throws ResourceNotFoundExceotion {
        return topicService.updateTopicById(topic_id, topicDTODetails);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteTopicById(@PathVariable (value = "id") Long topic_id) throws ResourceNotFoundExceotion {
        return topicService.deleteTopicById(topic_id);
    }
}