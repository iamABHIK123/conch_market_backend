package com.ecommerce.conchMarket.service;

import com.ecommerce.conchMarket.Repository.SubCategoryRepo;
import com.ecommerce.conchMarket.utility.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    private SubCategoryRepo subCategoryRepo;

    @Override
    public SubCategory createSubCategory(SubCategory subCategory) {
        return subCategoryRepo.save(subCategory);
    }

    @Override
    public SubCategory getSubCategoryById(Long id) {
        return subCategoryRepo.findById(id).orElseThrow(() -> new RuntimeException("SubCategory not found"));
    }

    @Override
    public List<SubCategory> getAllSubCategories() {
        return subCategoryRepo.findAll();
    }

    @Override
    public List<SubCategory> getSubCategoriesByCategoryId(Long categoryId) {
        return subCategoryRepo.findByCategoryId(categoryId);
    }

    @Override
    public SubCategory updateSubCategory(Long id, SubCategory subCategory) {
        SubCategory existingSubCategory = getSubCategoryById(id);
        existingSubCategory.setName(subCategory.getName());
        existingSubCategory.setCategory(subCategory.getCategory());
        return subCategoryRepo.save(existingSubCategory);
    }

    @Override
    public void deleteSubCategory(Long id) {
        subCategoryRepo.deleteById(id);
    }
}