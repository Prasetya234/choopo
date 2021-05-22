package com.example.choopo.service;

import com.example.choopo.dto.TopicDTO;
import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Topic;
import com.example.choopo.repository.TopicRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.*;
@Service
public class TopicService {

    @Autowired private TopicRepository topicRepository;

    @Autowired private ModelMapper modelMapper;

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
    public TopicDTO createTopic (TopicDTO topicDTO){
        Topic topic = mapToEntity(topicDTO);
        Topic newTopic = topicRepository.save(topic);

        TopicDTO postResponse = mapToDTO(topic);
        return postResponse;

    }

    // GET TOPIC BY ID
    public ResponseEntity<TopicDTO> getTopicById(Long topic_id) throws ResourceNotFoundExceotion {

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
    public TopicDTO updateTopicById(Long topic_id, @Valid TopicDTO topicDTODetails) throws ResourceNotFoundExceotion {
        Topic topic = topicRepository.findById(topic_id)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("TOPIC ID NOT FOUND"));

        topic.setTopicName(topicDTODetails.getTopicName());
        topic.setTopicCode(topicDTODetails.getTopicCode());
        final Topic updateTopicById = topicRepository.save(topic);

        return mapToDTO(updateTopicById);
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

    // CONVERT DTO TO ENTITY
    private TopicDTO mapToDTO(Topic topic) {
        TopicDTO topicDTO = modelMapper.map(topic, TopicDTO.class);

        return  topicDTO;
    }


    // CONVERT DTO TO ENTITY
    private Topic mapToEntity(TopicDTO topicDTO) {
        Topic topic = modelMapper.map(topicDTO, Topic.class);

        return  topic;
    }
}
