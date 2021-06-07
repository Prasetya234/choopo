package com.example.choopo.web.repository;

import com.example.choopo.web.model.Body;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BodyRepository extends JpaRepository<Body, Long> {
    @Query(value = "SELECT b.* FROM body b WHERE b.article_id = :article_id ", nativeQuery = true)
    List<Body> findByArticleId(@Param("article_id") String article_id);

}
