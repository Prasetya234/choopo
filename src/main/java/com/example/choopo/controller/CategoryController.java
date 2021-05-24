package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Category;
import com.example.choopo.service.CategoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired private CategoryImpl categoryService;

    @GetMapping("/")
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @PostMapping("/")
    public Category createCategory(@Valid @RequestBody Category categoryRequire){
        return categoryService.createCategory(categoryRequire);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable (value = "id") Long categoryId) throws ResourceNotFoundExceotion{
        return categoryService.getCategoryById(categoryId);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable(value = "id") Long categoryId, @Valid @RequestBody Category categoryDetails) throws ResourceNotFoundExceotion {
        return categoryService.updateCategory(categoryId, categoryDetails);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteCategoryById(@PathVariable (value = "id") Long categoryId) throws ResourceNotFoundExceotion {
        return categoryService.deleteCategoryById(categoryId);
    }
}