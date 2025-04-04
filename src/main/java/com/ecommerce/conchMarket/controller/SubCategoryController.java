package com.ecommerce.conchMarket.controller;

import com.ecommerce.conchMarket.Repository.CategoryRepo;
import com.ecommerce.conchMarket.Repository.SubCategoryRepo;
import com.ecommerce.conchMarket.dto.SubCategoryDTO;
import com.ecommerce.conchMarket.service.SubCategoryService;
import com.ecommerce.conchMarket.utility.Category;
import com.ecommerce.conchMarket.utility.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subcategories")
public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;
    
    @Autowired
    private CategoryRepo categoryRepo;
    
    @Autowired
    private SubCategoryRepo  subCategoryRepo;

//    @PostMapping
//    public ResponseEntity<SubCategory> createSubCategory(@RequestBody SubCategory subCategory) {
//        return ResponseEntity.ok(subCategoryService.createSubCategory(subCategory));
//    }
    @PostMapping
    public ResponseEntity<SubCategory> createSubCategory(@RequestBody SubCategoryDTO subCategoryDTO) {
        SubCategory subCategory = new SubCategory();
        subCategory.setName(subCategoryDTO.getName());

        // Fetch the category by ID and set it
        Category category = categoryRepo.findById(subCategoryDTO.getCategoryId())
            .orElseThrow(() -> new RuntimeException("Category not found"));
        subCategory.setCategory(category);

        SubCategory savedSubCategory = subCategoryRepo.save(subCategory);
        return ResponseEntity.ok(savedSubCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubCategory> getSubCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(subCategoryService.getSubCategoryById(id));
    }

    @GetMapping
    public ResponseEntity<List<SubCategory>> getAllSubCategories() {
        return ResponseEntity.ok(subCategoryService.getAllSubCategories());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<SubCategory>> getSubCategoriesByCategoryId(@PathVariable Long categoryId) {
        return ResponseEntity.ok(subCategoryService.getSubCategoriesByCategoryId(categoryId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubCategory> updateSubCategory(@PathVariable Long id, @RequestBody SubCategory subCategory) {
        return ResponseEntity.ok(subCategoryService.updateSubCategory(id, subCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubCategory(@PathVariable Long id) {
        subCategoryService.deleteSubCategory(id);
        return ResponseEntity.noContent().build();
    }
}