package com.ecommerce.conchMarket.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.conchMarket.utility.Product;

@Repository
public interface PoductRepo extends JpaRepository<Product, Long> {
	  Product findByName(String name);
	  List<Product> findByCategory(String category);
	  @Query("SELECT COUNT(DISTINCT p.category) FROM Product p")
	  Long countDistinctCategories();
}
