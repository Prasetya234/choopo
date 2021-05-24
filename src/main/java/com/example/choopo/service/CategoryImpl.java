package com.example.choopo.service;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Category;
import com.example.choopo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryImpl implements CategoryService{

    @Autowired private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category categoryRequire) {
        return categoryRepository.save(categoryRequire);
    }

    @Override
    public Category getCategoryById(Long categoryId) throws ResourceNotFoundExceotion {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundExceotion("CATEGORY ID NOT FOUND"));
    }

    @Override
    public Category updateCategory(Long categoryId, Category categoryDetails) throws ResourceNotFoundExceotion {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundExceotion("CATEGORY ID NOT FOUND"));

        category.setParentId(categoryDetails.getParentId());
        category.setCategoryName(categoryDetails.getCategoryName());
        final Category updateData = categoryRepository.save(category);
        return updateData;
    }

    @Override
    public Map<String, Boolean> deleteCategoryById(Long categoryId) throws ResourceNotFoundExceotion {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundExceotion("CATEGORY ID NOT FOUND"));

        categoryRepository.delete(category);

        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);
        return response;
    }

//    @Autowired private ModelMapper modelMapper;



//    // CONVERT DTO TO ENTITY
//    private CategoryDTO mapToDTO(Category category) {
//        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
//
//        return  categoryDTO;
//    }
//
//
//    // CONVERT DTO TO ENTITY
//    private Category mapToEntity(CategoryDTO categoryDTO) {
//        Category category = modelMapper.map(categoryDTO, Category.class);
//
//        return  category;
//    }
}
