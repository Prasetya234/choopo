package com.example.choopo.repository;

import com.example.choopo.model.Article;
import com.example.choopo.model.ArticleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query(value = "SELECT b.* FROM article b ORDER BY rand() LIMIT 5", nativeQuery = true)
    List<Article> articleScramble();

    @Query(value = "select c.* from article c order by c.total_view desc limit 10", nativeQuery = true)
    List<Article> articleTopTen();

    @Query(value = "select d.* from article d order by d.created_date desc limit 10", nativeQuery = true)
    List<Article> articleLatestNews();

    @Query(value = "select e.* from article e where article_status_id = :status", nativeQuery = true)
    List<Article> anonymousView(String status);

    @Query(value = "select e.* from article e where e.article_status_id = :status order by rand()", nativeQuery = true)
    List<Article> anonymousViewScramble(String status);
}
