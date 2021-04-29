package com.example.choopo.controller;

import com.example.choopo.exception.ResourceNotFoundExceotion;
import com.example.choopo.model.Category;
import com.example.choopo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/")
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    @PostMapping("/")
    public Category createCategory(@Valid @RequestBody Category category){
        return categoryRepository.save(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable (value = "id")Long category_id)
            throws ResourceNotFoundExceotion{
        Category category = categoryRepository.findById(category_id).orElseThrow(() -> new ResourceNotFoundExceotion("CATEGORY ID NOTFOUND"));
        return ResponseEntity.ok().body(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable (value = "id")Long category_id,@Valid @RequestBody Category categoryDetails)
            throws ResourceNotFoundExceotion{
        Category category = categoryRepository.findById(category_id).orElseThrow(() -> new ResourceNotFoundExceotion("CATEGORY ID NOTFOUND"));

        category.setCategory_name(categoryDetails.getCategory_name());
        category.setParent_id(categoryDetails.getParent_id());
        final Category updateCategory = categoryRepository.save(category);
        return ResponseEntity.ok(updateCategory);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteCategoryById(@PathVariable (value = "id") Long category_id)
            throws ResourceNotFoundExceotion {
        Category category = categoryRepository.findById(category_id).orElseThrow(() -> new ResourceNotFoundExceotion("CATEGORY ID NOTFOUND"));

        categoryRepository.delete(category);
        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);
        return response;
    }
}
