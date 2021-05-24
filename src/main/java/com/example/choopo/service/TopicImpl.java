package com.example.choopo.service;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Topic;
import com.example.choopo.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class TopicImpl implements TopicService{

    @Autowired private TopicRepository topicRepository;

    @Override
    public List<Topic> getAll() {
        return topicRepository.findAll();
    }

    @Override
    public Topic createTopic(Topic topicRequire) {
        return topicRepository.save(topicRequire);
    }

    @Override
    public Topic getTopicById(Long topicId) throws ResourceNotFoundExceotion {
        return topicRepository.findById(topicId).orElseThrow(() -> new ResourceNotFoundExceotion("TOPIC ID NOT FOUND"));
    }

    @Override
    public Topic updateTopicById(Long topicId, Topic topicDetails) throws ResourceNotFoundExceotion {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new ResourceNotFoundExceotion("TOPIC ID NOT FOUND"));

        topic.setTopicName(topicDetails.getTopicName());
        topic.setTopicCode(topicDetails.getTopicCode());
        final Topic updateData = topicRepository.save(topic);
        return updateData;
    }

    @Override
    public Map<String, Boolean> deleteTopicById(Long topicId) throws ResourceNotFoundExceotion {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new ResourceNotFoundExceotion("TOPIC ID NOT FOUND"));

        topicRepository.delete(topic);

        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);
        return response;
    }

//    @Autowired private ModelMapper modelMapper;



//
//    // CONVERT DTO TO ENTITY
//    private TopicDTO mapToDTO(Topic topic) {
//        TopicDTO topicDTO = modelMapper.map(topic, TopicDTO.class);
//
//        return  topicDTO;
//    }
//
//
//    // CONVERT DTO TO ENTITY
//    private Topic mapToEntity(TopicDTO topicDTO) {
//        Topic topic = modelMapper.map(topicDTO, Topic.class);
//
//        return  topic;
//    }
}
