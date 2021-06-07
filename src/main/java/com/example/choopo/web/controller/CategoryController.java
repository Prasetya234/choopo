package com.example.choopo.web.controller;

import com.example.choopo.web.dto.CategoryDTO;
import com.example.choopo.web.exception.ResourceNotFoundExceotion;
import com.example.choopo.web.model.Category;
import com.example.choopo.web.response.CommonResponse;
import com.example.choopo.web.response.CommonResponseGenerator;
import com.example.choopo.web.service.CategoryImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Stream;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired private CategoryImpl categoryService;

    @Autowired private ModelMapper modelMapper;

    @Autowired private CommonResponseGenerator commonResponseGenerator;

    @GetMapping("/")
    public CommonResponse<CategoryDTO> getAll() {
        Stream<Object> categoryList = categoryService.getAll()
                .stream()
                .map(category
                        -> modelMapper.map(category, CategoryDTO.class));

        return commonResponseGenerator.successResponse(categoryList);
    }

    @PostMapping("/")
    public CommonResponse<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTORequire){
        Category categoryRequire = modelMapper.map(categoryDTORequire, Category.class);
        Category category = categoryService.createCategory(categoryRequire);

        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        return commonResponseGenerator.successResponse(categoryDTO);
    }

    @GetMapping("/{id}")
    public CommonResponse<CategoryDTO> getCategoryById(@PathVariable (value = "id") Long categoryId) throws ResourceNotFoundExceotion{
        Category category = categoryService.getCategoryById(categoryId);

        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);

        return commonResponseGenerator.successResponse(categoryDTO);
    }

    @PutMapping("/{id}")
    public CommonResponse<CategoryDTO> updateCategory(@PathVariable(value = "id") Long categoryId, @Valid @RequestBody CategoryDTO categoryDTODetails) throws ResourceNotFoundExceotion {
        Category categoryDetails = modelMapper.map(categoryDTODetails, Category.class);
        Category category = categoryService.updateCategory(categoryId, categoryDetails);

        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        return commonResponseGenerator.successResponse(category);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteCategoryById(@PathVariable (value = "id") Long categoryId) throws ResourceNotFoundExceotion {
        return categoryService.deleteCategoryById(categoryId);
    }
}