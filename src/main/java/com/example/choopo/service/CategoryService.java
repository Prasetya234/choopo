package com.example.choopo.service;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    List<Category> getAll();

    Category createCategory(Category categoryRequire);

    Category getCategoryById(Long categoryId) throws ResourceNotFoundExceotion;

    Category updateCategory(Long categoryId, Category categoryDetails) throws  ResourceNotFoundExceotion;

    Map<String, Boolean> deleteCategoryById(Long category_id) throws ResourceNotFoundExceotion;
}
