package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Topic;
import com.example.choopo.response.CommonResponse;
import com.example.choopo.response.CommonResponseGenerator;
import com.example.choopo.service.TopicImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired private TopicImpl topicService;

    @Autowired private CommonResponseGenerator commonResponseGenerator;

    @GetMapping("/")
    public CommonResponse<List<Topic>> getAll() {
        List<Topic> topicList = topicService.getAll();

        return commonResponseGenerator.successResponse(topicList);
    }

    @PostMapping("/")
    public CommonResponse<Topic> createTopic (@Valid @RequestBody Topic topicRequire){
        Topic topic = topicService.createTopic(topicRequire);

        return commonResponseGenerator.successResponse(topic);
    }

    @GetMapping("/{id}")
    public CommonResponse<Topic> getTopicById(@PathVariable (value = "id")Long topicId) throws ResourceNotFoundExceotion {
        Topic topic = topicService.getTopicById(topicId);

        return commonResponseGenerator.successResponse(topic);
    }

    @PutMapping("/{id}")
    public CommonResponse<Topic> updateTopicById(@PathVariable(value = "id") Long topicId, @Valid @RequestBody Topic topicDetails) throws ResourceNotFoundExceotion {
        Topic topic = topicService.updateTopicById(topicId, topicDetails);

        return commonResponseGenerator.successResponse(topic);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteTopicById(@PathVariable (value = "id") Long topicId) throws ResourceNotFoundExceotion {
        return topicService.deleteTopicById(topicId);
    }
}