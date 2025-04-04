package com.ecommerce.conchMarket.service;

import com.ecommerce.conchMarket.utility.SubCategory;
import java.util.List;

public interface SubCategoryService {
    SubCategory createSubCategory(SubCategory subCategory);
    SubCategory getSubCategoryById(Long id);
    List<SubCategory> getAllSubCategories();
    List<SubCategory> getSubCategoriesByCategoryId(Long categoryId);
    SubCategory updateSubCategory(Long id, SubCategory subCategory);
    void deleteSubCategory(Long id);
}