package com.example.choopo.service;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Category;
import com.example.choopo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {

    @Autowired private CategoryRepository categoryRepository;

    // GET ALL CATEGORY
    public ResponseEntity<Map<String, Object>> getAll() {
        try {
            List<Category> categories = new ArrayList<>();

            categories = categoryRepository.findAll();

            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", categories);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // POST CATEGORY
    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    // GET CATEGORY BY ID
    public ResponseEntity<Category> getCategoryById(Long category_id) throws ResourceNotFoundExceotion {
        Optional<Category> category = Optional.ofNullable(categoryRepository.findById(category_id)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("CATEGORY ID NOT FOUND")));

        try {
            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", category);

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            ResourceNotFoundExceotion message = new ResourceNotFoundExceotion("CATEGORY ID NOT FOUND");
            return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
        }
    }

    // UPDATE CATEGORY BY ID
    public ResponseEntity<Category> updateCategory(Long category_id, Category categoryDetails) throws ResourceNotFoundExceotion{
        Category category = categoryRepository.findById(category_id)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("CATEGORY ID NOTFOUND"));

        category.setCategoryName(categoryDetails.getCategoryName());
        category.setParentId(categoryDetails.getParentId());

        final Category updateCategory = categoryRepository.save(category);
        return ResponseEntity.ok(updateCategory);
    }

    // DELETE CATEGORY BY ID
    public Map<String, Boolean> deleteCategoryById(Long category_id) throws ResourceNotFoundExceotion {
        Category category = categoryRepository.findById(category_id)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("CATEGORY ID NOTFOUND"));

        categoryRepository.delete(category);

        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);

        return response;
    }
}
