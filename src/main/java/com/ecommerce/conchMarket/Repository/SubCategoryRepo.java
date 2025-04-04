package com.ecommerce.conchMarket.Repository;

import com.ecommerce.conchMarket.utility.SubCategory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepo extends JpaRepository<SubCategory, Long> {
    // Find all subcategories by category ID
    List<SubCategory> findByCategoryId(Long categoryId);
}