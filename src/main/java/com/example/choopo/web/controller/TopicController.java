package com.example.choopo.web.controller;

import com.example.choopo.web.dto.TopicDTO;
import com.example.choopo.web.exception.ResourceNotFoundExceotion;
import com.example.choopo.web.model.Topic;
import com.example.choopo.web.response.CommonResponse;
import com.example.choopo.web.response.CommonResponseGenerator;
import com.example.choopo.web.service.TopicImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Stream;

@RestController
@PreAuthorize("hasAnyAuthority('ADMIN', 'WRITER')")
@RequestMapping("/topic")
public class TopicController {

    @Autowired private TopicImpl topicService;

    @Autowired private ModelMapper modelMapper;

    @Autowired private CommonResponseGenerator commonResponseGenerator;

    @GetMapping("/")
    public CommonResponse<List<TopicDTO>> getAll() {

        Stream<Object> topicList = topicService.getAll().stream().map(topic -> modelMapper.map(topic, TopicDTO.class));

        return commonResponseGenerator.successResponse(topicList);
    }

    @PostMapping("/")
    public CommonResponse<TopicDTO> createTopic (@Valid @RequestBody TopicDTO topicDTORequire){

        Topic topicRequire = modelMapper.map(topicDTORequire, Topic.class);

        Topic topic = topicService.createTopic(topicRequire);

        TopicDTO topicDTO = modelMapper.map(topic, TopicDTO.class);

        return commonResponseGenerator.successResponse(topicDTO);
    }

    @GetMapping("/{id}")
    public CommonResponse<TopicDTO> getTopicById(@PathVariable (value = "id")Long topicId) throws ResourceNotFoundExceotion {

        Topic topic = topicService.getTopicById(topicId);

        TopicDTO topicDTO = modelMapper.map(topic, TopicDTO.class);

        return commonResponseGenerator.successResponse(topicDTO);
    }

    @PutMapping("/{id}")
    public CommonResponse<TopicDTO> updateTopicById(@PathVariable(value = "id") Long topicId, @Valid @RequestBody TopicDTO topicDTODetails) throws ResourceNotFoundExceotion {
        Topic topicDetails = modelMapper.map(topicDTODetails, Topic.class);

        Topic topic = topicService.updateTopicById(topicId, topicDetails);

        TopicDTO topicDTO = modelMapper.map(topic, TopicDTO.class);
        return commonResponseGenerator.successResponse(topicDTO);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteTopicById(@PathVariable (value = "id") Long topicId) throws ResourceNotFoundExceotion {
        return topicService.deleteTopicById(topicId);
    }
}