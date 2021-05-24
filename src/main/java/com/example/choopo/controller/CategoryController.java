package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Category;
import com.example.choopo.response.CommonResponse;
import com.example.choopo.response.CommonResponseGenerator;
import com.example.choopo.service.CategoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired private CategoryImpl categoryService;

    @Autowired private CommonResponseGenerator commonResponseGenerator;

    @GetMapping("/")
    public CommonResponse<List<Category>> getAll() {
        List<Category> categoryList = categoryService.getAll();

        return commonResponseGenerator.successResponse(categoryList);
    }

    @PostMapping("/")
    public CommonResponse<Category> createCategory(@Valid @RequestBody Category categoryRequire){
        Category category = categoryService.createCategory(categoryRequire);

        return commonResponseGenerator.successResponse(category);
    }

    @GetMapping("/{id}")
    public CommonResponse<Category> getCategoryById(@PathVariable (value = "id") Long categoryId) throws ResourceNotFoundExceotion{
        Category category = categoryService.getCategoryById(categoryId);

        return commonResponseGenerator.successResponse(category);
    }

    @PutMapping("/{id}")
    public CommonResponse<Category> updateCategory(@PathVariable(value = "id") Long categoryId, @Valid @RequestBody Category categoryDetails) throws ResourceNotFoundExceotion {
        Category category = categoryService.updateCategory(categoryId, categoryDetails);

        return commonResponseGenerator.successResponse(category);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteCategoryById(@PathVariable (value = "id") Long categoryId) throws ResourceNotFoundExceotion {
        return categoryService.deleteCategoryById(categoryId);
    }
}