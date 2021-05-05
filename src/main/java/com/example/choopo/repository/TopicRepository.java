package com.example.choopo.repository;

import com.example.choopo.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {
//    @Query(value = "SELECT b.* FROM body b", nativeQuery = true)
//    List<Topic> findAll();
}
