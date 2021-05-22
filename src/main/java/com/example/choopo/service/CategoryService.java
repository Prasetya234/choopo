package com.example.choopo.service;

import com.example.choopo.dto.CategoryDTO;
import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Category;
import com.example.choopo.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {

    @Autowired private CategoryRepository categoryRepository;

    @Autowired private ModelMapper modelMapper;


    // GET ALL CATEGORY
    public ResponseEntity<Map<String, Object>> getAll() {

            List<Category> categories = new ArrayList<>();

            categories = categoryRepository.findAll();

            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", categories);

            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // POST CATEGORY
    public CategoryDTO createCategory(CategoryDTO categoryDTO){
        Category category = mapToEntity(categoryDTO);
        Category newCategory = categoryRepository.save(category);

        CategoryDTO postResponse = mapToDTO(newCategory);
        return postResponse;
    }

    // GET CATEGORY BY ID
    public ResponseEntity<CategoryDTO> getCategoryById(Long category_id) throws ResourceNotFoundExceotion {
        Optional<Category> category = Optional.ofNullable(categoryRepository.findById(category_id)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("CATEGORY ID NOT FOUND")));

            Map<String, Object> response = new HashMap<>();
            response.put("message","SUCCESS");
            response.put("status","SUCCESS");
            response.put("content", category);

            return new ResponseEntity(response, HttpStatus.OK);
    }

    // UPDATE CATEGORY BY ID
    public CategoryDTO updateCategory(Long category_id, CategoryDTO categoryDTODetails) throws ResourceNotFoundExceotion{
        Category category = categoryRepository.findById(category_id)
                .orElseThrow(() ->
                        new ResourceNotFoundExceotion("CATEGORY ID NOTFOUND"));

        category.setCategoryName(categoryDTODetails.getCategoryName());
        category.setParentId(categoryDTODetails.getParentId());

        final Category updateCategory = categoryRepository.save(category);
        return mapToDTO(updateCategory);

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

    // CONVERT DTO TO ENTITY
    private CategoryDTO mapToDTO(Category category) {
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);

        return  categoryDTO;
    }


    // CONVERT DTO TO ENTITY
    private Category mapToEntity(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);

        return  category;
    }
}
