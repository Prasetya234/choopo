package com.example.choopo.repository;

import com.example.choopo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
