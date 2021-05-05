package com.example.choopo.repository;

import com.example.choopo.model.Body;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BodyRepository extends JpaRepository<Body, Long> {
    @Query(value = "SELECT b.* FROM body b WHERE b.article_id = :article_id ", nativeQuery = true)
    Body findByArticleId(@Param("article_id") String article_id);

}
