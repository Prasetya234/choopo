package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Topic;
import com.example.choopo.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    public TopicRepository topicRepository;

    @GetMapping("/")
    public List<Topic> getAllTopic(){
        return topicRepository.findAll();
    }

    @PostMapping("/")
    public Topic createTopic (@Valid @RequestBody Topic topic){
        return topicRepository.save(topic);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable (value = "id")Long topic_id)
            throws ResourceNotFoundExceotion {
        Topic topic = topicRepository.findById(topic_id).orElseThrow(() -> new ResourceNotFoundExceotion("TOPIC ID NOTFOUND"));
        return ResponseEntity.ok().body(topic);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topic> updateTopicById(@PathVariable (value = "id")Long topic_id, @Valid @RequestBody Topic topicDetails)
            throws ResourceNotFoundExceotion {
        Topic topic = topicRepository.findById(topic_id).orElseThrow(() -> new ResourceNotFoundExceotion("TOPIC ID NOTFOUND"));

        topic.setTopic_name(topicDetails.getTopic_name());
        topic.setTopic_code(topicDetails.getTopic_code());
        final Topic updateTopicById = topicRepository.save(topic);
        return ResponseEntity.ok(updateTopicById);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteTopicById(@PathVariable (value = "id") Long topic_id)
            throws ResourceNotFoundExceotion {
        Topic topic = topicRepository.findById(topic_id).orElseThrow(() -> new ResourceNotFoundExceotion("TOPIC ID NOTFOUND"));

        topicRepository.delete(topic);
        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);
        return response;
    }
}
