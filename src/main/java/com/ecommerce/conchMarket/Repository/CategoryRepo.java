package com.ecommerce.conchMarket.Repository;
import com.ecommerce.conchMarket.utility.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    // Custom query methods can be added here if needed
}