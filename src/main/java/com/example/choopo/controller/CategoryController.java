package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Category;
import com.example.choopo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getAll() {
        return categoryService.getAll();
    }

    @PostMapping("/")
    public Category createCategory(@Valid @RequestBody Category category){
        return categoryService.createCategory(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable (value = "id") Long category_id) throws ResourceNotFoundExceotion{
        return categoryService.getCategoryById(category_id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable (value = "id") Long category_id,@Valid @RequestBody Category categoryDetails) throws ResourceNotFoundExceotion{
        return categoryService.updateCategory(category_id, categoryDetails);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteCategoryById(@PathVariable (value = "id") Long category_id) throws ResourceNotFoundExceotion {
        return categoryService.deleteCategoryById(category_id);
    }
}